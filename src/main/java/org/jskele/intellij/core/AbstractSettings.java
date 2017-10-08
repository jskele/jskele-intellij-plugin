package org.jskele.intellij.core;

import com.intellij.util.xmlb.annotations.Tag;

public abstract class AbstractSettings {

	@Tag
	public String daoInterfaceName = Constants.DEFAULT_DAO_INTERFACE_NAME;

	@Tag
	public String generateSqlAnnotationName = Constants.DEFAULT_GENERATE_SQL_ANNOTATION_NAME;

}
