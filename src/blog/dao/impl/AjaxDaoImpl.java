package blog.dao.impl;

import org.springframework.stereotype.Repository;

import blog.dao.AjaxDao;
import blog.dao.base.DaoSupport;

@Repository("ajaxDao")
public class AjaxDaoImpl extends DaoSupport<String> implements AjaxDao {

}
