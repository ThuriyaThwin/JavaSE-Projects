package com.jdc.code;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class ResourceGenerator {

	private static String model = "/Users/minlwin/git/se-projects/smart-hotel/smart-hotel-svc/src/main/java/com/jdc/hotel/resources";
	private static String entity = "/Users/minlwin/git/se-projects/smart-hotel/smart-hotel-ejb/src/main/java/com/jdc/hotel/entity";

	public static void main(String[] args) {
		File entityFolder = new File(entity);

		for (File f : entityFolder.listFiles()) {
			if (needToCreateModel(f)) {
				String fName = f.getName();
				createModelClass(fName.substring(0, fName.length() - 5));
			}
		}
	}

	static void createModelClass(String name) {

		File modelFolder = new File(model);
		String fileName = String.format("%sResource.java", name);

		try (PrintWriter out = new PrintWriter(new FileOutputStream(new File(modelFolder, fileName)))) {

			out.println("package com.jdc.hotel.resources;");
			out.println("");
			out.println("import javax.inject.Inject;");
			out.println("import javax.ws.rs.Path;");
			out.println("");
			out.println(String.format("import com.jdc.hotel.entity.%s;", name));
			out.println("import com.jdc.hotel.model.AbstractModel;");
			out.println(String.format("import com.jdc.hotel.model.%sModel;", name));
			out.println("");
			out.println(String.format("@Path(\"%s\")", name));
			out.println(String.format("public class %sResource extends AbstractResource<%s> {", name, name));
			out.println("");
			out.println("	@Inject");
			out.println(String.format("	private %sModel model;", name));
			out.println("");
			out.println("	@Override");
			out.println(String.format("	protected AbstractModel<%s> getModel() {", name));
			out.println("		return model;");
			out.println("	}");
			out.println("");
			out.println("	@Override");
			out.println("	protected String getAllQuery() {");
			out.println("		return \"" + String.format("select t from %s t ", name) + "\";");
			out.println("	}");
			out.println("");
			out.println("}");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static boolean needToCreateModel(File f) {

		try {
			List<String> lines = Files.lines(f.toPath()).map(l -> l.trim())
					.filter(l -> l.startsWith("@Entity") || l.contains("abstract")).collect(Collectors.toList());

			if (f.getName().startsWith("Account")) {
				return false;
			}

			if (lines.size() == 1 && lines.get(0).equals("@Entity")) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}
