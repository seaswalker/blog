package blog.dao.impl;

import org.springframework.stereotype.Repository;

import blog.dao.LinkDao;
import blog.dao.base.DaoSupport;
import blog.model.Link;

@Repository("linkDao")
public class LinkDaoImpl extends DaoSupport<Link> implements LinkDao {

}
