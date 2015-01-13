/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.sesame.marketdata;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.core.link.SecurityLink;
import com.opengamma.core.security.Security;
import com.opengamma.core.value.MarketDataRequirementNames;
import com.opengamma.id.ExternalIdBundle;
import com.opengamma.util.ArgumentChecker;

/**
 * Key for looking up the market data associated with a security.
 *
 * @param <T> the type of the market data
 * @param <S> the type of the security
 */
@BeanDefinition(builderScope = "private")
public final class SecurityId<T, S extends Security> implements MarketDataId<T>, ImmutableBean {

  /** ID of the security. */
  @PropertyDefinition(validate = "notNull")
  private final ExternalIdBundle _id;

  /** Link to the security object. */
  @PropertyDefinition(validate = "notNull", get = "private")
  private final SecurityLink<S> _securityLink;

  /** Expected type of the market data value. */
  @PropertyDefinition(validate = "notNull")
  private final Class<T> _marketDataType;

  /** Name of the field in the market data record that holds the market data. */
  @PropertyDefinition(validate = "notNull")
  private final FieldName _fieldName;

  /**
   * Creates a key for requesting market data for a security.
   *
   * @param <T> the type of the market data
   * @param <S> the type of the security whose market data is required
   * @param security the security whose market data is required
   * @param dataType the type of the market data
   * @param fieldName the field name of the data in the underlying market data record
   * @return a key for requesting market data for the security
   */
  public static <T, S extends Security> SecurityId<T, S> of(S security, Class<T> dataType, FieldName fieldName) {
    ArgumentChecker.notNull(security, "security");
    return new SecurityId<>(security.getExternalIdBundle(), SecurityLink.resolved(security), dataType, fieldName);
  }

  /**
   * Creates a key for requesting the market value of a security.
   * <p>
   * The field name used in the market data lookup is {@link MarketDataRequirementNames#MARKET_VALUE}.
   *
   * @param id ID of the security whose market data is required
   * @return a key for requesting market data for the security
   */
  public static SecurityId<Double, Security> of(ExternalIdBundle id) {
    return new SecurityId<>(id, SecurityLink.resolvable(id), Double.class, MarketDataUtils.MARKET_VALUE);
  }

  /**
   * Creates a key for requesting market data for a security.
   *
   * @param <T> the type of the market data
   * @param id ID of the security
   * @param dataType the type of the market data
   * @param fieldName the field name of the data in the underlying market data record
   * @return a key for requesting market data for the security
   */
  public static <T> SecurityId<T, Security> of(ExternalIdBundle id, Class<T> dataType, FieldName fieldName) {
    ArgumentChecker.notNull(id, "id");
    return new SecurityId<>(id, SecurityLink.resolvable(id), dataType, fieldName);
  }

  /**
   * Creates a key for requesting the market value of a security.
   * <p>
   * The field name used in the market data lookup is {@link MarketDataRequirementNames#MARKET_VALUE}.
   *
   * @param security the security whose market data is required
   * @param <S> the type of the security whose market data is required
   * @return a key for requesting market data for the security
   */
  public static <S extends Security> SecurityId<Double, S> of(S security) {
    ArgumentChecker.notNull(security, "security");
    return new SecurityId<>(security.getExternalIdBundle(),
                            SecurityLink.resolved(security),
                            Double.class,
                            MarketDataUtils.MARKET_VALUE);
  }

  /**
   * @return the security identified by this ID, potentially loading it from a database
   */
  public Security resolveSecurity() {
    return _securityLink.resolve();
  }

  @Override
  public int hashCode() {
    // this was written manually rather than generated by Joda Beans so that the link could be ignored.
    // two IDs should be equal if they refer to the same security, regardless of whether one has a resolved
    // link and the other has a resolvable link
    return Objects.hash(_id, _marketDataType, _fieldName);
  }

  @Override
  public boolean equals(Object obj) {
    // this was written manually rather than generated by Joda Beans so that the link could be ignored.
    // two IDs should be equal if they refer to the same security, regardless of whether one has a resolved
    // link and the other has a resolvable link
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    SecurityId other = (SecurityId) obj;
    return
        Objects.equals(this._id, other._id) &&
        Objects.equals(this._marketDataType, other._marketDataType) &&
        Objects.equals(this._fieldName, other._fieldName);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code SecurityId}.
   * @return the meta-bean, not null
   */
  @SuppressWarnings("rawtypes")
  public static SecurityId.Meta meta() {
    return SecurityId.Meta.INSTANCE;
  }

  /**
   * The meta-bean for {@code SecurityId}.
   * @param <R>  the first generic type
   * @param <S>  the second generic type
   * @param cls1  the first generic type
   * @param cls2  the second generic type
   * @return the meta-bean, not null
   */
  @SuppressWarnings("unchecked")
  public static <R, S extends Security> SecurityId.Meta<R, S> metaSecurityId(Class<R> cls1, Class<S> cls2) {
    return SecurityId.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(SecurityId.Meta.INSTANCE);
  }

  private SecurityId(
      ExternalIdBundle id,
      SecurityLink<S> securityLink,
      Class<T> marketDataType,
      FieldName fieldName) {
    JodaBeanUtils.notNull(id, "id");
    JodaBeanUtils.notNull(securityLink, "securityLink");
    JodaBeanUtils.notNull(marketDataType, "marketDataType");
    JodaBeanUtils.notNull(fieldName, "fieldName");
    this._id = id;
    this._securityLink = securityLink;
    this._marketDataType = marketDataType;
    this._fieldName = fieldName;
  }

  @SuppressWarnings("unchecked")
  @Override
  public SecurityId.Meta<T, S> metaBean() {
    return SecurityId.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets iD of the security.
   * @return the value of the property, not null
   */
  public ExternalIdBundle getId() {
    return _id;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets link to the security object.
   * @return the value of the property, not null
   */
  private SecurityLink<S> getSecurityLink() {
    return _securityLink;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets expected type of the market data value.
   * @return the value of the property, not null
   */
  public Class<T> getMarketDataType() {
    return _marketDataType;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets name of the field in the market data record that holds the market data.
   * @return the value of the property, not null
   */
  public FieldName getFieldName() {
    return _fieldName;
  }

  //-----------------------------------------------------------------------
  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(160);
    buf.append("SecurityId{");
    buf.append("id").append('=').append(getId()).append(',').append(' ');
    buf.append("securityLink").append('=').append(getSecurityLink()).append(',').append(' ');
    buf.append("marketDataType").append('=').append(getMarketDataType()).append(',').append(' ');
    buf.append("fieldName").append('=').append(JodaBeanUtils.toString(getFieldName()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code SecurityId}.
   * @param <T>  the type
   * @param <S>  the type
   */
  public static final class Meta<T, S extends Security> extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    @SuppressWarnings("rawtypes")
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code id} property.
     */
    private final MetaProperty<ExternalIdBundle> _id = DirectMetaProperty.ofImmutable(
        this, "id", SecurityId.class, ExternalIdBundle.class);
    /**
     * The meta-property for the {@code securityLink} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<SecurityLink<S>> _securityLink = DirectMetaProperty.ofImmutable(
        this, "securityLink", SecurityId.class, (Class) SecurityLink.class);
    /**
     * The meta-property for the {@code marketDataType} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Class<T>> _marketDataType = DirectMetaProperty.ofImmutable(
        this, "marketDataType", SecurityId.class, (Class) Class.class);
    /**
     * The meta-property for the {@code fieldName} property.
     */
    private final MetaProperty<FieldName> _fieldName = DirectMetaProperty.ofImmutable(
        this, "fieldName", SecurityId.class, FieldName.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "id",
        "securityLink",
        "marketDataType",
        "fieldName");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3355:  // id
          return _id;
        case 807992154:  // securityLink
          return _securityLink;
        case 843057760:  // marketDataType
          return _marketDataType;
        case 1265009317:  // fieldName
          return _fieldName;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends SecurityId<T, S>> builder() {
      return new SecurityId.Builder<T, S>();
    }

    @SuppressWarnings({"unchecked", "rawtypes" })
    @Override
    public Class<? extends SecurityId<T, S>> beanType() {
      return (Class) SecurityId.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code id} property.
     * @return the meta-property, not null
     */
    public MetaProperty<ExternalIdBundle> id() {
      return _id;
    }

    /**
     * The meta-property for the {@code securityLink} property.
     * @return the meta-property, not null
     */
    public MetaProperty<SecurityLink<S>> securityLink() {
      return _securityLink;
    }

    /**
     * The meta-property for the {@code marketDataType} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Class<T>> marketDataType() {
      return _marketDataType;
    }

    /**
     * The meta-property for the {@code fieldName} property.
     * @return the meta-property, not null
     */
    public MetaProperty<FieldName> fieldName() {
      return _fieldName;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 3355:  // id
          return ((SecurityId<?, ?>) bean).getId();
        case 807992154:  // securityLink
          return ((SecurityId<?, ?>) bean).getSecurityLink();
        case 843057760:  // marketDataType
          return ((SecurityId<?, ?>) bean).getMarketDataType();
        case 1265009317:  // fieldName
          return ((SecurityId<?, ?>) bean).getFieldName();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code SecurityId}.
   * @param <T>  the type
   * @param <S>  the type
   */
  private static final class Builder<T, S extends Security> extends DirectFieldsBeanBuilder<SecurityId<T, S>> {

    private ExternalIdBundle _id;
    private SecurityLink<S> _securityLink;
    private Class<T> _marketDataType;
    private FieldName _fieldName;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3355:  // id
          return _id;
        case 807992154:  // securityLink
          return _securityLink;
        case 843057760:  // marketDataType
          return _marketDataType;
        case 1265009317:  // fieldName
          return _fieldName;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder<T, S> set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 3355:  // id
          this._id = (ExternalIdBundle) newValue;
          break;
        case 807992154:  // securityLink
          this._securityLink = (SecurityLink<S>) newValue;
          break;
        case 843057760:  // marketDataType
          this._marketDataType = (Class<T>) newValue;
          break;
        case 1265009317:  // fieldName
          this._fieldName = (FieldName) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder<T, S> set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder<T, S> setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder<T, S> setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder<T, S> setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public SecurityId<T, S> build() {
      return new SecurityId<T, S>(
          _id,
          _securityLink,
          _marketDataType,
          _fieldName);
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(160);
      buf.append("SecurityId.Builder{");
      buf.append("id").append('=').append(JodaBeanUtils.toString(_id)).append(',').append(' ');
      buf.append("securityLink").append('=').append(JodaBeanUtils.toString(_securityLink)).append(',').append(' ');
      buf.append("marketDataType").append('=').append(JodaBeanUtils.toString(_marketDataType)).append(',').append(' ');
      buf.append("fieldName").append('=').append(JodaBeanUtils.toString(_fieldName));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
