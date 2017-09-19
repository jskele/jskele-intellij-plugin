package org.jskele.intellij;

public class Constants {

	public static final String ORG_JSKELE_LIBS_DAO_DAO = "org.jskele.libs.dao.Dao";
	public static final String GENERATE_SQL_ANNOTATION = "org.jskele.libs.dao.GenerateSql";
	public static final String TEMPLATE_ANNOTATION = "org.jskele.libs.dao.SqlTemplate";

	static final String MISSING_ANNOTATIONS_ERROR = "Method does not have @GenerateSql annotation, but also does not have SQL file in classpath, please add one of them.";
	static final String BOTH_ANNOTATIONS_PRESENT_ERROR = "Method has @GenerateSql annotation, but also SQL file in classpath, please remove one of them.";
}
