/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.sesame.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.ImmutableConstructor;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.google.common.collect.ImmutableMap;
import com.opengamma.util.ArgumentChecker;

/**
 * Configuration for the user-specified arguments of a functions in the function model.
 */
@BeanDefinition
public class FunctionArguments implements ImmutableBean {

  /** Some empty arguments */
  public static final FunctionArguments EMPTY = new FunctionArguments(Collections.<String, Object>emptyMap());

  /**
   * The function arguments keyed by parameter name.
   */
  @PropertyDefinition(validate = "notNull")
  private final ImmutableMap<String, Object> _arguments;

  /**
   * Creates an instance.
   *
   * @param arguments  the arguments, not null
   */
  @ImmutableConstructor
  public FunctionArguments(Map<String, Object> arguments) {
    _arguments = ImmutableMap.copyOf(ArgumentChecker.notNull(arguments, "arguments"));
  }

  /**
   * Returns the argument for the named parameter or null if there isn't one.
   *
   * @param parameterName  the name of the parameter
   * @return  the argument value or null if there isn't one
   */
  public Object getArgument(String parameterName) {
    return _arguments.get(parameterName);
  }

  /**
   * Merges these arguments with another set, arguments from this instance take priority where there are duplicates.
   *
   * @param other arguments to merge with
   * @return the union of the configuration with settings from this instance taking priority
   */
  public FunctionArguments mergedWith(FunctionArguments other, FunctionArguments... others) {
    ArgumentChecker.notNull(other, "other");
    Map<String, Object> arguments = new HashMap<>();
    // reverse the list because the later args have lower priority. so they have to go in the map first
    // and get overwritten by the later args
    List<FunctionArguments> reversedOthers = new ArrayList<>(Arrays.asList(others));
    Collections.reverse(reversedOthers);

    for (FunctionArguments args : reversedOthers) {
      arguments.putAll(args._arguments);
    }
    arguments.putAll(other._arguments);
    arguments.putAll(_arguments);
    return new FunctionArguments(arguments);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code FunctionArguments}.
   * @return the meta-bean, not null
   */
  public static FunctionArguments.Meta meta() {
    return FunctionArguments.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(FunctionArguments.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static FunctionArguments.Builder builder() {
    return new FunctionArguments.Builder();
  }

  @Override
  public FunctionArguments.Meta metaBean() {
    return FunctionArguments.Meta.INSTANCE;
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
   * Gets the function arguments keyed by parameter name.
   * @return the value of the property, not null
   */
  public ImmutableMap<String, Object> getArguments() {
    return _arguments;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder toBuilder() {
    return new Builder(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      FunctionArguments other = (FunctionArguments) obj;
      return JodaBeanUtils.equal(getArguments(), other.getArguments());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getArguments());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(64);
    buf.append("FunctionArguments{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("arguments").append('=').append(JodaBeanUtils.toString(getArguments())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code FunctionArguments}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code arguments} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<ImmutableMap<String, Object>> _arguments = DirectMetaProperty.ofImmutable(
        this, "arguments", FunctionArguments.class, (Class) ImmutableMap.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "arguments");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -2035517098:  // arguments
          return _arguments;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public FunctionArguments.Builder builder() {
      return new FunctionArguments.Builder();
    }

    @Override
    public Class<? extends FunctionArguments> beanType() {
      return FunctionArguments.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code arguments} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ImmutableMap<String, Object>> arguments() {
      return _arguments;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -2035517098:  // arguments
          return ((FunctionArguments) bean).getArguments();
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
   * The bean-builder for {@code FunctionArguments}.
   */
  public static class Builder extends DirectFieldsBeanBuilder<FunctionArguments> {

    private Map<String, Object> _arguments = new HashMap<String, Object>();

    /**
     * Restricted constructor.
     */
    protected Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    protected Builder(FunctionArguments beanToCopy) {
      this._arguments = new HashMap<String, Object>(beanToCopy.getArguments());
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -2035517098:  // arguments
          return _arguments;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -2035517098:  // arguments
          this._arguments = (Map<String, Object>) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public FunctionArguments build() {
      return new FunctionArguments(
          _arguments);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the {@code arguments} property in the builder.
     * @param arguments  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder arguments(Map<String, Object> arguments) {
      JodaBeanUtils.notNull(arguments, "arguments");
      this._arguments = arguments;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(64);
      buf.append("FunctionArguments.Builder{");
      int len = buf.length();
      toString(buf);
      if (buf.length() > len) {
        buf.setLength(buf.length() - 2);
      }
      buf.append('}');
      return buf.toString();
    }

    protected void toString(StringBuilder buf) {
      buf.append("arguments").append('=').append(JodaBeanUtils.toString(_arguments)).append(',').append(' ');
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
