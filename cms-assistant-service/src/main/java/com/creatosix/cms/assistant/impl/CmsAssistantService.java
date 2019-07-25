package com.creatosix.cms.assistant.impl;

import com.creatosix.cms.assistant.CmsAssistant;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetEntryServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * Implementations of utilities to asssist with CMS operations. Add methods here that augmnent the 
 * CMS development experience. Methods defined here can be invoked from CMS templates.
 * 
 * @author javeedchida
 */
@Component(
	immediate = true,
	property = {
		// TODO enter required service properties
	},
	service = CmsAssistant.class
)
public class CmsAssistantService implements CmsAssistant {

	@Override
	public List<AssetEntry> getAssetEntriesByGroupIdAndStructureName(Long groupId, String structureName) throws PortalException {
		List<AssetEntry> assetEntries = new ArrayList<AssetEntry>();
		try {
			AssetEntryQuery assetEntryQuery = new AssetEntryQuery();
			assetEntryQuery.setClassName(JournalArticle.class.getName());
			List<DDMStructure> structures = DDMStructureLocalServiceUtil.getStructures(groupId);
			//find the FAQ structure
			long[] structureIds = new long[1];
			for (DDMStructure structure : structures) {
				if (structure.getName(Locale.getDefault()).equalsIgnoreCase(structureName)) {
					structureIds[0] = structure.getStructureId();
					break;
				}
			}
			if (structureIds[0] == 0) {
				throw new RuntimeException("Invalid structure name");
			} else {
				assetEntryQuery.setClassTypeIds(structureIds);
				assetEntries = AssetEntryServiceUtil.getEntries(assetEntryQuery);	
			}			
		} catch (Exception exc) {
			exc.printStackTrace();
		} 
		return assetEntries;
	}

	@Override
	public List<AssetCategory> getAssetCategoriesByGroupIdAndVocabularyName(Long groupId, String vocabularyName)
			throws PortalException {
		List<AssetVocabulary> vocabularies = AssetVocabularyServiceUtil.getGroupVocabularies(groupId);
		for (AssetVocabulary vocabulary : vocabularies) {
			if (vocabulary.getName().equalsIgnoreCase(vocabularyName)) {
				System.out.printf("-- found %d categories", vocabulary.getCategories().size());
				return vocabulary.getCategories();
			}
		}
		return null;
	}

}