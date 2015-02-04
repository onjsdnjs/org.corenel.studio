package org.corenel.core.common.dao;

import java.io.Serializable;
import java.util.List;

public interface CommonDAO extends Serializable {
	
	public <T> void batchUpdate(List<T> t, String sqlId);
	
}
