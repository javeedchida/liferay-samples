package com.creatosix.cms.assistant;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

public interface CmsAssistant {
	
	/**
	 * Retrieve a list of AssetEntry references for the specified groupId and structure name.
	 * 
	 * @param groupId
	 * @param structureName
	 * @return list of AssetEntry references matching parameters
	 * @throws PortalException, RuntimeException if structure name is invalid
	 */
	public List<AssetEntry> getAssetEntriesByGroupIdAndStructureName(Long groupId, String structureName) throws PortalException ;

}
