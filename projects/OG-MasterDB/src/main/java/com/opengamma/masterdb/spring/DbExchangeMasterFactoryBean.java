/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.masterdb.spring;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.core.change.JmsChangeManager;
import com.opengamma.masterdb.exchange.DbExchangeMaster;

/**
 * Spring factory bean to create the database exchange master.
 */
@BeanDefinition
public class DbExchangeMasterFactoryBean extends AbstractDbMasterFactoryBean<DbExchangeMaster> {

  /**
   * Creates an instance.
   */
  public DbExchangeMasterFactoryBean() {
    super(DbExchangeMaster.class);
  }

  //-------------------------------------------------------------------------
  @Override
  protected DbExchangeMaster createObject() {
    DbExchangeMaster master = new DbExchangeMaster(getDbConnector());
    if (getUniqueIdScheme() != null) {
      master.setUniqueIdScheme(getUniqueIdScheme());
    }
    if (getMaxRetries() != null) {
      master.setMaxRetries(getMaxRetries());
    }
    if (getJmsConnector() != null) {
      JmsChangeManager cm = new JmsChangeManager(getJmsConnector().ensureTopicName(getJmsChangeManagerTopic()));
      master.setChangeManager(cm);
      cm.start();
    }
    return master;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code DbExchangeMasterFactoryBean}.
   * @return the meta-bean, not null
   */
  public static DbExchangeMasterFactoryBean.Meta meta() {
    return DbExchangeMasterFactoryBean.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(DbExchangeMasterFactoryBean.Meta.INSTANCE);
  }

  @Override
  public DbExchangeMasterFactoryBean.Meta metaBean() {
    return DbExchangeMasterFactoryBean.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      return super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code DbExchangeMasterFactoryBean}.
   */
  public static class Meta extends AbstractDbMasterFactoryBean.Meta<DbExchangeMaster> {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
      this, (DirectMetaPropertyMap) super.metaPropertyMap());

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    public BeanBuilder<? extends DbExchangeMasterFactoryBean> builder() {
      return new DirectBeanBuilder<DbExchangeMasterFactoryBean>(new DbExchangeMasterFactoryBean());
    }

    @Override
    public Class<? extends DbExchangeMasterFactoryBean> beanType() {
      return DbExchangeMasterFactoryBean.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
