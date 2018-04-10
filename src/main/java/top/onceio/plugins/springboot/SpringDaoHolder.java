package top.onceio.plugins.springboot;

import java.lang.reflect.Type;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;

import top.onceio.beans.ApiMethod;
import top.onceio.db.dao.Cnd;
import top.onceio.db.dao.Dao;
import top.onceio.db.dao.Page;
import top.onceio.db.dao.impl.DaoHelper;
import top.onceio.db.dao.tpl.SelectTpl;
import top.onceio.db.dao.tpl.UpdateTpl;
import top.onceio.db.tbl.OEntity;
import top.onceio.mvc.annocations.Api;
import top.onceio.mvc.annocations.Param;
import top.onceio.util.OReflectUtil;

public abstract class SpringDaoHolder<T extends OEntity> implements Dao<T> {
	@Autowired
	protected DaoHelper daoHelper;
	private Class<T> tbl;
	@SuppressWarnings("unchecked")
	public SpringDaoHolder() {
		Type t = SpringDaoHolder.class.getTypeParameters()[0];
		tbl = (Class<T>) OReflectUtil.searchGenType(SpringDaoHolder.class, this.getClass(), t);
	}

	public DaoHelper getDaoHelper() {
		return daoHelper;
	}

	public void setDaoHelper(DaoHelper daoHelper) {
		this.daoHelper = daoHelper;
	}

	@Api(method = { ApiMethod.GET, ApiMethod.POST })
	@Override
	public T get(@Param("id") Long id) {
		daoHelper.get(tbl, id);
		return daoHelper.get(tbl, id);
	}

	@Api(method = ApiMethod.PUT)
	@Override
	public T insert(@Param("entity") T entity) {
		return daoHelper.insert(entity);
	}

	@Api(method = ApiMethod.PUT)
	@Override
	public int batchInsert(@Param("entities") List<T> entities) {
		return daoHelper.batchInsert(entities);
	}

	@Api(method = ApiMethod.PUT)
	@Override
	public int update(@Param("entity") T entity) {
		return daoHelper.update(entity);
	}

	@Api(method = ApiMethod.PATCH)
	@Override
	public int updateIgnoreNull(@Param("entity") T entity) {
		return daoHelper.updateIgnoreNull(entity);
	}

	@Override
	public int updateByTpl(UpdateTpl<T> tpl) {
		return daoHelper.updateByTpl(tbl, tpl);
	}

	@Api(method = ApiMethod.PATCH)
	@Override
	public int updateByTplCnd(@Param("tpl") UpdateTpl<T> tpl, @Param("cnd") Cnd<T> cnd) {
		return daoHelper.updateByTplCnd(tbl, tpl, cnd);
	}

	@Api(method = {ApiMethod.REMOVE,ApiMethod.GET})
	@Override
	public int removeById(@Param("id") Long id) {
		return daoHelper.removeById(tbl, id);
	}

	@Api(method = ApiMethod.REMOVE)
	@Override
	public int removeByIds(@Param("ids") List<Long> ids) {
		return daoHelper.removeByIds(tbl, ids);
	}

	@Api(method = ApiMethod.REMOVE)
	@Override
	public int remove(@Param("cnd") Cnd<T> cnd) {
		return daoHelper.remove(tbl, cnd);
	}

	@Api(method = ApiMethod.RECOVERY)
	@Override
	public int recovery(@Param("cnd") Cnd<T> cnd) {
		return daoHelper.recovery(tbl, cnd);
	}

	@Override
	public int deleteById(Long id) {
		return daoHelper.deleteById(tbl, id);
	}

	@Override
	public int deleteByIds(List<Long> ids) {
		return daoHelper.deleteByIds(tbl, ids);
	}

	@Override
	public int delete(Cnd<T> cnd) {
		return daoHelper.delete(tbl, cnd);
	}

	@Api(method = ApiMethod.RECOVERY)
	@Override
	public T fetch(@Param("tpl") SelectTpl<T> tpl, @Param("cnd") Cnd<T> cnd) {
		return daoHelper.fetch(tbl, tpl, cnd);
	}

	@Api(method = { ApiMethod.GET, ApiMethod.POST })
	@Override
	public List<T> findByIds(@Param("ids") List<Long> ids) {
		return daoHelper.findByIds(tbl, ids);
	}

	@Api(method = { ApiMethod.GET, ApiMethod.POST })
	@Override
	public Page<T> find(@Param("cnd") Cnd<T> cnd) {
		return daoHelper.find(tbl, cnd);
	}

	@Api(method = { ApiMethod.GET, ApiMethod.POST })
	@Override
	public Page<T> findTpl(@Param("tpl") SelectTpl<T> tpl, @Param("cnd") Cnd<T> cnd) {
		return daoHelper.findByTpl(tbl, tpl, cnd);
	}

	@Api(method = { ApiMethod.GET, ApiMethod.POST })
	@Override
	public void download(@Param("tpl") SelectTpl<T> tpl, @Param("cnd") Cnd<T> cnd, Consumer<T> consumer) {
		daoHelper.download(tbl, tpl, cnd, consumer);
	}

	@Override
	public long count() {
		return daoHelper.count(tbl);
	}

	@Override
	public long count(Cnd<T> cnd) {
		return daoHelper.count(tbl, cnd);
	}

}
