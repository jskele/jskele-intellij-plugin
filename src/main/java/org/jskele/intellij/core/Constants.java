package org.jskele.intellij.core;

public class Constants {

	public static final String DEFAULT_DAO_INTERFACE_NAME = "org.jskele.libs.dao.Dao";
	public static final String DEFAULT_GENERATE_SQL_ANNOTATION_NAME = "org.jskele.libs.dao.GenerateSql";
	public static final String DEFAULT_TEMPLATE_ANNOTATION = "org.jskele.libs.dao.SqlTemplate";

	public static final String MISSING_ANNOTATIONS_ERROR = "Method does not have @GenerateSql annotation, but also does not have SQL file in classpath, please add one of them.";
	public static final String BOTH_ANNOTATIONS_PRESENT_ERROR = "Method has @GenerateSql annotation, but also SQL file in classpath, please remove one of them.";
}
