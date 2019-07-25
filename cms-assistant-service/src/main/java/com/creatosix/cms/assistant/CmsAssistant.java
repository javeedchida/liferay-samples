package com.creatosix.cms.assistant;

import com.liferay.asset.kernel.model.AssetCategory;
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
	
	/**
	 * Retrieve a list of AssetCategory references for the specified groupId and vocabulary
	 * @param groupId
	 * @param vocabularyName
	 * @return a List of AssetCategory references, null if there is no match
	 * @throws PortalException
	 */
	public List<AssetCategory> getAssetCategoriesByGroupIdAndVocabularyName(Long groupId, String vocabularyName) throws PortalException ;

}
