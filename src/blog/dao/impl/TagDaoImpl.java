package blog.dao.impl;

import org.springframework.stereotype.Repository;

import blog.dao.TagDao;
import blog.dao.base.DaoSupport;
import blog.model.Tag;

@Repository("tagDao")
public class TagDaoImpl extends DaoSupport<Tag> implements TagDao {

}
