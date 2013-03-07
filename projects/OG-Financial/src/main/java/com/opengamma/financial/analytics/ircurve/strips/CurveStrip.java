/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve.strips;

import java.io.Serializable;
import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.id.MutableUniqueIdentifiable;
import com.opengamma.id.UniqueId;
import com.opengamma.util.time.Tenor;

/**
 *
 */
@BeanDefinition
public abstract class CurveStrip extends DirectBean implements Serializable, MutableUniqueIdentifiable, Comparable<CurveStrip> {

  /** Serialization version */
  private static final long serialVersionUID = 1L;

  /**
   * The unique identifier of the strip.
   */
  @PropertyDefinition
  private UniqueId _uniqueId;

  /**
   * The curve specification builder name.
   */
  @PropertyDefinition(validate = "notNull")
  private String _curveSpecificationName;

  /* package */CurveStrip() {
    super();
  }

  public CurveStrip(final String curveSpecificationName) {
    setCurveSpecificationName(curveSpecificationName);
  }

  protected abstract Tenor getResolvedMaturity();

  @Override
  public int compareTo(final CurveStrip other) {
    final int result = getResolvedMaturity().compareTo(other.getResolvedMaturity());
    if (result != 0) {
      return result;
    }
    return getClass().getName().compareTo(other.getClass().getName());
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code CurveStrip}.
   * @return the meta-bean, not null
   */
  public static CurveStrip.Meta meta() {
    return CurveStrip.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(CurveStrip.Meta.INSTANCE);
  }

  @Override
  public CurveStrip.Meta metaBean() {
    return CurveStrip.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -294460212:  // uniqueId
        return getUniqueId();
      case 264065279:  // curveSpecificationName
        return getCurveSpecificationName();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -294460212:  // uniqueId
        setUniqueId((UniqueId) newValue);
        return;
      case 264065279:  // curveSpecificationName
        setCurveSpecificationName((String) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_curveSpecificationName, "curveSpecificationName");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      CurveStrip other = (CurveStrip) obj;
      return JodaBeanUtils.equal(getUniqueId(), other.getUniqueId()) &&
          JodaBeanUtils.equal(getCurveSpecificationName(), other.getCurveSpecificationName());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getUniqueId());
    hash += hash * 31 + JodaBeanUtils.hashCode(getCurveSpecificationName());
    return hash;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the unique identifier of the strip.
   * @return the value of the property
   */
  public UniqueId getUniqueId() {
    return _uniqueId;
  }

  /**
   * Sets the unique identifier of the strip.
   * @param uniqueId  the new value of the property
   */
  public void setUniqueId(UniqueId uniqueId) {
    this._uniqueId = uniqueId;
  }

  /**
   * Gets the the {@code uniqueId} property.
   * @return the property, not null
   */
  public final Property<UniqueId> uniqueId() {
    return metaBean().uniqueId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the curve specification builder name.
   * @return the value of the property, not null
   */
  public String getCurveSpecificationName() {
    return _curveSpecificationName;
  }

  /**
   * Sets the curve specification builder name.
   * @param curveSpecificationName  the new value of the property, not null
   */
  public void setCurveSpecificationName(String curveSpecificationName) {
    JodaBeanUtils.notNull(curveSpecificationName, "curveSpecificationName");
    this._curveSpecificationName = curveSpecificationName;
  }

  /**
   * Gets the the {@code curveSpecificationName} property.
   * @return the property, not null
   */
  public final Property<String> curveSpecificationName() {
    return metaBean().curveSpecificationName().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code CurveStrip}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code uniqueId} property.
     */
    private final MetaProperty<UniqueId> _uniqueId = DirectMetaProperty.ofReadWrite(
        this, "uniqueId", CurveStrip.class, UniqueId.class);
    /**
     * The meta-property for the {@code curveSpecificationName} property.
     */
    private final MetaProperty<String> _curveSpecificationName = DirectMetaProperty.ofReadWrite(
        this, "curveSpecificationName", CurveStrip.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "uniqueId",
        "curveSpecificationName");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          return _uniqueId;
        case 264065279:  // curveSpecificationName
          return _curveSpecificationName;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends CurveStrip> builder() {
      throw new UnsupportedOperationException("CurveStrip is an abstract class");
    }

    @Override
    public Class<? extends CurveStrip> beanType() {
      return CurveStrip.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code uniqueId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<UniqueId> uniqueId() {
      return _uniqueId;
    }

    /**
     * The meta-property for the {@code curveSpecificationName} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> curveSpecificationName() {
      return _curveSpecificationName;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
