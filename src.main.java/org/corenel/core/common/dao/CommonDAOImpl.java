package org.corenel.core.common.dao;

import java.sql.SQLException;
import java.util.List;
import org.corenel.core.common.ServiceExceptionResolver;

@SuppressWarnings("serial")
public class CommonDAOImpl implements CommonDAO{
		
	@SuppressWarnings("deprecation")
	public <T> void batchUpdate(List<T> t, String sqlId) {
		/*try {
			
			getSqlMapClient().startTransaction();
			getSqlMapClient().startBatch();
			for (T data : t) {
				getSqlMapClient().insert(sqlId, data);
			}
			getSqlMapClient().executeBatch();
			getSqlMapClient().commitTransaction();
			
		} catch (SQLException e) {
			throw new ServiceExceptionResolver(e);
		}finally{
			try {
				getSqlMapClient().endTransaction();
			} catch (SQLException e) {
				throw new ServiceExceptionResolver(e);
			}
			
		}*/
	}
	
}

