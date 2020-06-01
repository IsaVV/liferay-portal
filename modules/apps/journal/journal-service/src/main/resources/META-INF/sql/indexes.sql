create index IX_F1C2B662 on JournalArticle (DDMStructureKey[$COLUMN_LENGTH:75$], ctCollectionId);
create index IX_A01DB92F on JournalArticle (DDMTemplateKey[$COLUMN_LENGTH:75$], ctCollectionId);
create index IX_1C4302D3 on JournalArticle (classNameId, DDMTemplateKey[$COLUMN_LENGTH:75$], ctCollectionId);
create index IX_CB14A5FE on JournalArticle (classNameId, expirationDate, status, ctCollectionId);
create index IX_AAF3B581 on JournalArticle (companyId, ctCollectionId);
create index IX_6D9F9567 on JournalArticle (companyId, status, ctCollectionId);
create index IX_F12AB4A3 on JournalArticle (companyId, version, ctCollectionId);
create index IX_F97E5289 on JournalArticle (companyId, version, status, ctCollectionId);
create index IX_62EBBA43 on JournalArticle (ctCollectionId);
create index IX_55CADE3F on JournalArticle (displayDate, status, ctCollectionId);
create index IX_70325BE2 on JournalArticle (groupId, DDMStructureKey[$COLUMN_LENGTH:75$], ctCollectionId);
create index IX_6A6363AF on JournalArticle (groupId, DDMTemplateKey[$COLUMN_LENGTH:75$], ctCollectionId);
create index IX_E10314FA on JournalArticle (groupId, articleId[$COLUMN_LENGTH:75$], ctCollectionId);
create index IX_9E386BE0 on JournalArticle (groupId, articleId[$COLUMN_LENGTH:75$], status, ctCollectionId);
create unique index IX_D3ACAD4A on JournalArticle (groupId, articleId[$COLUMN_LENGTH:75$], version, ctCollectionId);
create index IX_8A0FEBBE on JournalArticle (groupId, classNameId, DDMStructureKey[$COLUMN_LENGTH:75$], ctCollectionId);
create index IX_20E66853 on JournalArticle (groupId, classNameId, DDMTemplateKey[$COLUMN_LENGTH:75$], ctCollectionId);
create index IX_E748358 on JournalArticle (groupId, classNameId, classPK, ctCollectionId);
create index IX_6CA45D20 on JournalArticle (groupId, classNameId, layoutUuid[$COLUMN_LENGTH:75$], ctCollectionId);
create index IX_1ED664C3 on JournalArticle (groupId, ctCollectionId);
create index IX_8868DCD0 on JournalArticle (groupId, folderId, classNameId, status, ctCollectionId);
create index IX_74060760 on JournalArticle (groupId, folderId, ctCollectionId);
create index IX_D36D9846 on JournalArticle (groupId, folderId, status, ctCollectionId);
create index IX_E6BD667C on JournalArticle (groupId, layoutUuid[$COLUMN_LENGTH:75$], ctCollectionId);
create index IX_E107E2A9 on JournalArticle (groupId, status, ctCollectionId);
create index IX_254CBF60 on JournalArticle (groupId, urlTitle[$COLUMN_LENGTH:255$], ctCollectionId);
create index IX_E7C5046 on JournalArticle (groupId, urlTitle[$COLUMN_LENGTH:255$], status, ctCollectionId);
create index IX_E20FD06D on JournalArticle (groupId, userId, classNameId, ctCollectionId);
create index IX_B2F1D3FD on JournalArticle (groupId, userId, ctCollectionId);
create index IX_75FE7BFC on JournalArticle (layoutUuid[$COLUMN_LENGTH:75$], ctCollectionId);
create index IX_7D1E8774 on JournalArticle (resourcePrimKey, ctCollectionId);
create index IX_60EF9564 on JournalArticle (resourcePrimKey, indexable, ctCollectionId);
create index IX_DFAE24A on JournalArticle (resourcePrimKey, indexable, status, ctCollectionId);
create index IX_115EC45A on JournalArticle (resourcePrimKey, status, ctCollectionId);
create index IX_DAB0F686 on JournalArticle (smallImageId, ctCollectionId);
create index IX_584284F7 on JournalArticle (uuid_[$COLUMN_LENGTH:75$], companyId, ctCollectionId);
create index IX_9603F88D on JournalArticle (uuid_[$COLUMN_LENGTH:75$], ctCollectionId);
create unique index IX_4D5E99B9 on JournalArticle (uuid_[$COLUMN_LENGTH:75$], groupId, ctCollectionId);

create index IX_E69312A1 on JournalArticleLocalization (articlePK, ctCollectionId);
create unique index IX_5593D868 on JournalArticleLocalization (articlePK, languageId[$COLUMN_LENGTH:75$], ctCollectionId);
create unique index IX_9B44D13C on JournalArticleLocalization (companyId, articlePK, languageId[$COLUMN_LENGTH:75$], ctCollectionId);
create unique index IX_97DC9F36 on JournalArticleLocalization (companyId, articlePK, title[$COLUMN_LENGTH:400$], languageId[$COLUMN_LENGTH:75$], ctCollectionId);
create index IX_429AC23C on JournalArticleLocalization (ctCollectionId);

create index IX_B332E3F1 on JournalArticleResource (ctCollectionId);
create unique index IX_57129BA8 on JournalArticleResource (groupId, articleId[$COLUMN_LENGTH:75$], ctCollectionId);
create index IX_FB783ED5 on JournalArticleResource (groupId, ctCollectionId);
create index IX_D83FDF25 on JournalArticleResource (uuid_[$COLUMN_LENGTH:75$], companyId, ctCollectionId);
create index IX_63192F1F on JournalArticleResource (uuid_[$COLUMN_LENGTH:75$], ctCollectionId);
create unique index IX_37A8A767 on JournalArticleResource (uuid_[$COLUMN_LENGTH:75$], groupId, ctCollectionId);

create index IX_9207CB31 on JournalContentSearch (articleId[$COLUMN_LENGTH:75$]);
create index IX_42F51F38 on JournalContentSearch (companyId);
create index IX_6838E427 on JournalContentSearch (groupId, articleId[$COLUMN_LENGTH:75$]);
create index IX_7CC7D73E on JournalContentSearch (groupId, privateLayout, articleId[$COLUMN_LENGTH:75$]);
create unique index IX_C3AA93B8 on JournalContentSearch (groupId, privateLayout, layoutId, portletId[$COLUMN_LENGTH:200$], articleId[$COLUMN_LENGTH:75$]);
create index IX_8DAF8A35 on JournalContentSearch (portletId[$COLUMN_LENGTH:200$]);

create unique index IX_65576CBC on JournalFeed (groupId, feedId[$COLUMN_LENGTH:75$]);
create index IX_CB37A10F on JournalFeed (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_39031F51 on JournalFeed (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_6ADEEEBB on JournalFolder (companyId, ctCollectionId);
create index IX_E6C2F4A1 on JournalFolder (companyId, status, ctCollectionId);
create index IX_3C880149 on JournalFolder (ctCollectionId);
create index IX_6CD2A47D on JournalFolder (groupId, ctCollectionId);
create index IX_39FA42FC on JournalFolder (groupId, name[$COLUMN_LENGTH:100$], ctCollectionId);
create index IX_766B0E24 on JournalFolder (groupId, parentFolderId, ctCollectionId);
create unique index IX_A2109363 on JournalFolder (groupId, parentFolderId, name[$COLUMN_LENGTH:100$], ctCollectionId);
create index IX_BFD19B0A on JournalFolder (groupId, parentFolderId, status, ctCollectionId);
create index IX_95F9567D on JournalFolder (uuid_[$COLUMN_LENGTH:75$], companyId, ctCollectionId);
create index IX_D7A0DEC7 on JournalFolder (uuid_[$COLUMN_LENGTH:75$], ctCollectionId);
create unique index IX_6F8814BF on JournalFolder (uuid_[$COLUMN_LENGTH:75$], groupId, ctCollectionId);