package com.jdc.code;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class CodeGenerator {

	private static String model = "/Users/minlwin/git/se-projects/smart-hotel/smart-hotel-ejb/src/main/java/com/jdc/hotel/model";
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
		String fileName = String.format("%sModel.java", name);

		try (PrintWriter out = new PrintWriter(new FileOutputStream(new File(modelFolder, fileName)))) {

			out.println("package com.jdc.hotel.model;");
			out.println("");
			out.println("import javax.ejb.LocalBean;");
			out.println("import javax.ejb.Stateless;");
			out.println("import javax.persistence.EntityManager;");
			out.println("import javax.persistence.PersistenceContext;");
			out.println("");
			out.println(String.format("import com.jdc.hotel.entity.%s;", name));
			out.println("");
			out.println("@LocalBean");
			out.println("@Stateless");
			out.println(String.format("public class %sModel extends AbstractModel<%s> {", name, name));
			out.println("");
			out.println("	@PersistenceContext");
			out.println("	private EntityManager em;");
			out.println("");
			out.println("	@Override");
			out.println(String.format("	protected Class<%s> getType() {", name));
			out.println(String.format("		return %s.class;", name));
			out.println("	}");
			out.println("");
			out.println("	@Override");
			out.println("	protected EntityManager getEm() {");
			out.println("		return em;");
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

			if (lines.size() == 1 && lines.get(0).equals("@Entity")) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}
