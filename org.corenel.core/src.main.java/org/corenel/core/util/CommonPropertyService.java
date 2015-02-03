package org.corenel.core.util;

import java.io.Serializable;

import org.springframework.stereotype.Component;


@SuppressWarnings("serial")
@Component("commonPropertiesService")
public class CommonPropertyService implements Serializable{
	
	public String getString(String key){
		if(key.equals("cert")){
			return "D:\\fdkPG:\\workspace\\PayMgr\\src\\main\\webapp\\config\\fdpg_cert.pem";
		}else if(key.equals("ApproveServerIp")){
			return "210.182.8.109";
		}else if(key.equals("FDversion")){
			return "10";
		}
		return null;
	}
}
