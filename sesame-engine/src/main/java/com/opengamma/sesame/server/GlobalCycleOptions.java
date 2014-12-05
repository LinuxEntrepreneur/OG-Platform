/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.sesame.server;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.ImmutableValidator;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import org.threeten.bp.ZonedDateTime;

import com.google.common.base.Supplier;
import com.opengamma.engine.marketdata.spec.MarketDataSpecification;
import com.opengamma.sesame.engine.CalculationArguments;
import com.opengamma.util.ArgumentChecker;

/**
 * Contains cycle options that can be used to execute a view across multiple
 * cycles (potentially infinite).
 *
 * @deprecated use {@link CalculationArguments}
 */
@Deprecated
@BeanDefinition
public final class GlobalCycleOptions implements ImmutableBean, CycleOptions {

  /**
   * When using live market data, indicates whether to wait for all the
   * subscriptions to be resolved. Therefore if true and using a UI,
   * "market data pending" messages will never be seen.
   */
  @PropertyDefinition(validate = "notNull")
  private final boolean _awaitAllMarketData;
  /**
   * Number of cycles to run.
   * Zero or less indicates to run forever.
   */
  // todo this works but is somewhat dependent on magic values
  @PropertyDefinition
  private final int _numCycles;
  /**
   * The specification for the market data to use during the execution.
   */
  // todo - should be able to specify a generator which either returns a fixed spec on every call or can produce a sequence of values e.g. different historic dates
  @PropertyDefinition(validate = "notNull")
  private final MarketDataSpecification _marketDataSpec;
  /**
   * The valuation time to be used during the execution. Either {@link #_valuationTime}
   * or {@link #_valuationTimeSupplier} must be supplied on construction. If both are
   * provided then the {@link #_valuationTimeSupplier} will be used as the source
   * of valuation times.
   */
  @PropertyDefinition
  private final ZonedDateTime _valuationTime;
  /**
   * A supplier of valuation times. This allows a different valuation time to
   * be used on each cycle if required. Either {@link #_valuationTime} or
   * {@link #_valuationTimeSupplier} must be supplied on construction. If both are
   * provided then the {@link #_valuationTimeSupplier} will be used as the source
   * of valuation times.
   */
  @PropertyDefinition
  private final Supplier<ZonedDateTime> _valuationTimeSupplier;

  @ImmutableValidator
  private void validate() {
    ArgumentChecker.isFalse(_valuationTime == null && _valuationTimeSupplier == null,
                            "Either valuationTime or valuationTimeSupplier must be non-null");
  }

  //-------------------------------------------------------------------------
  @Override
  public Iterator<IndividualCycleOptions> iterator() {
    return new Iterator<IndividualCycleOptions>() {

      /**
       * Index to keep track of how far through the iteration we are.
       */
      private int _index;

      private final Supplier<ZonedDateTime> _valuationTimeProvider = createValuationTimeSupplier();

      @Override
      public boolean hasNext() {
        return _numCycles <= 0 || _index < _numCycles;
      }

      @Override
      public IndividualCycleOptions next() {

        if (hasNext()) {

          IndividualCycleOptions cycleOptions = null;/*IndividualCycleOptions.builder()
              .marketDataSpecs(ImmutableList.of(_marketDataSpec))
              .valuationTime(_valuationTimeProvider.get())
              .build();*/
          _index++;
          return cycleOptions;
        } else {
          throw new NoSuchElementException();
        }
      }

      private Supplier<ZonedDateTime> createValuationTimeSupplier() {
        return _valuationTimeSupplier != null ?
            _valuationTimeSupplier :
            new Supplier<ZonedDateTime>() {
              @Override
              public ZonedDateTime get() {
                return _valuationTime;
              }
            };
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException("Removal is not permitted for this iterator");
      }
    };
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code GlobalCycleOptions}.
   * @return the meta-bean, not null
   */
  public static GlobalCycleOptions.Meta meta() {
    return GlobalCycleOptions.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(GlobalCycleOptions.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static GlobalCycleOptions.Builder builder() {
    return new GlobalCycleOptions.Builder();
  }

  private GlobalCycleOptions(
      boolean awaitAllMarketData,
      int numCycles,
      MarketDataSpecification marketDataSpec,
      ZonedDateTime valuationTime,
      Supplier<ZonedDateTime> valuationTimeSupplier) {
    JodaBeanUtils.notNull(awaitAllMarketData, "awaitAllMarketData");
    JodaBeanUtils.notNull(marketDataSpec, "marketDataSpec");
    this._awaitAllMarketData = awaitAllMarketData;
    this._numCycles = numCycles;
    this._marketDataSpec = marketDataSpec;
    this._valuationTime = valuationTime;
    this._valuationTimeSupplier = valuationTimeSupplier;
    validate();
  }

  @Override
  public GlobalCycleOptions.Meta metaBean() {
    return GlobalCycleOptions.Meta.INSTANCE;
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
   * Gets when using live market data, indicates whether to wait for all the
   * subscriptions to be resolved. Therefore if true and using a UI,
   * "market data pending" messages will never be seen.
   * @return the value of the property, not null
   */
  public boolean isAwaitAllMarketData() {
    return _awaitAllMarketData;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the numCycles.
   * @return the value of the property
   */
  public int getNumCycles() {
    return _numCycles;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the marketDataSpec.
   * @return the value of the property, not null
   */
  public MarketDataSpecification getMarketDataSpec() {
    return _marketDataSpec;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the valuation time to be used during the execution. Either {@link #_valuationTime}
   * or {@link #_valuationTimeSupplier} must be supplied on construction. If both are
   * provided then the {@link #_valuationTimeSupplier} will be used as the source
   * of valuation times.
   * @return the value of the property
   */
  public ZonedDateTime getValuationTime() {
    return _valuationTime;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets a supplier of valuation times. This allows a different valuation time to
   * be used on each cycle if required. Either {@link #_valuationTime} or
   * {@link #_valuationTimeSupplier} must be supplied on construction. If both are
   * provided then the {@link #_valuationTimeSupplier} will be used as the source
   * of valuation times.
   * @return the value of the property
   */
  public Supplier<ZonedDateTime> getValuationTimeSupplier() {
    return _valuationTimeSupplier;
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
      GlobalCycleOptions other = (GlobalCycleOptions) obj;
      return (isAwaitAllMarketData() == other.isAwaitAllMarketData()) &&
          (getNumCycles() == other.getNumCycles()) &&
          JodaBeanUtils.equal(getMarketDataSpec(), other.getMarketDataSpec()) &&
          JodaBeanUtils.equal(getValuationTime(), other.getValuationTime()) &&
          JodaBeanUtils.equal(getValuationTimeSupplier(), other.getValuationTimeSupplier());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(isAwaitAllMarketData());
    hash += hash * 31 + JodaBeanUtils.hashCode(getNumCycles());
    hash += hash * 31 + JodaBeanUtils.hashCode(getMarketDataSpec());
    hash += hash * 31 + JodaBeanUtils.hashCode(getValuationTime());
    hash += hash * 31 + JodaBeanUtils.hashCode(getValuationTimeSupplier());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(192);
    buf.append("GlobalCycleOptions{");
    buf.append("awaitAllMarketData").append('=').append(isAwaitAllMarketData()).append(',').append(' ');
    buf.append("numCycles").append('=').append(getNumCycles()).append(',').append(' ');
    buf.append("marketDataSpec").append('=').append(getMarketDataSpec()).append(',').append(' ');
    buf.append("valuationTime").append('=').append(getValuationTime()).append(',').append(' ');
    buf.append("valuationTimeSupplier").append('=').append(JodaBeanUtils.toString(getValuationTimeSupplier()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code GlobalCycleOptions}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code awaitAllMarketData} property.
     */
    private final MetaProperty<Boolean> _awaitAllMarketData = DirectMetaProperty.ofImmutable(
        this, "awaitAllMarketData", GlobalCycleOptions.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code numCycles} property.
     */
    private final MetaProperty<Integer> _numCycles = DirectMetaProperty.ofImmutable(
        this, "numCycles", GlobalCycleOptions.class, Integer.TYPE);
    /**
     * The meta-property for the {@code marketDataSpec} property.
     */
    private final MetaProperty<MarketDataSpecification> _marketDataSpec = DirectMetaProperty.ofImmutable(
        this, "marketDataSpec", GlobalCycleOptions.class, MarketDataSpecification.class);
    /**
     * The meta-property for the {@code valuationTime} property.
     */
    private final MetaProperty<ZonedDateTime> _valuationTime = DirectMetaProperty.ofImmutable(
        this, "valuationTime", GlobalCycleOptions.class, ZonedDateTime.class);
    /**
     * The meta-property for the {@code valuationTimeSupplier} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Supplier<ZonedDateTime>> _valuationTimeSupplier = DirectMetaProperty.ofImmutable(
        this, "valuationTimeSupplier", GlobalCycleOptions.class, (Class) Supplier.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "awaitAllMarketData",
        "numCycles",
        "marketDataSpec",
        "valuationTime",
        "valuationTimeSupplier");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -32240431:  // awaitAllMarketData
          return _awaitAllMarketData;
        case 780459891:  // numCycles
          return _numCycles;
        case 843018977:  // marketDataSpec
          return _marketDataSpec;
        case 113591406:  // valuationTime
          return _valuationTime;
        case -178301862:  // valuationTimeSupplier
          return _valuationTimeSupplier;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public GlobalCycleOptions.Builder builder() {
      return new GlobalCycleOptions.Builder();
    }

    @Override
    public Class<? extends GlobalCycleOptions> beanType() {
      return GlobalCycleOptions.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code awaitAllMarketData} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Boolean> awaitAllMarketData() {
      return _awaitAllMarketData;
    }

    /**
     * The meta-property for the {@code numCycles} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Integer> numCycles() {
      return _numCycles;
    }

    /**
     * The meta-property for the {@code marketDataSpec} property.
     * @return the meta-property, not null
     */
    public MetaProperty<MarketDataSpecification> marketDataSpec() {
      return _marketDataSpec;
    }

    /**
     * The meta-property for the {@code valuationTime} property.
     * @return the meta-property, not null
     */
    public MetaProperty<ZonedDateTime> valuationTime() {
      return _valuationTime;
    }

    /**
     * The meta-property for the {@code valuationTimeSupplier} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Supplier<ZonedDateTime>> valuationTimeSupplier() {
      return _valuationTimeSupplier;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -32240431:  // awaitAllMarketData
          return ((GlobalCycleOptions) bean).isAwaitAllMarketData();
        case 780459891:  // numCycles
          return ((GlobalCycleOptions) bean).getNumCycles();
        case 843018977:  // marketDataSpec
          return ((GlobalCycleOptions) bean).getMarketDataSpec();
        case 113591406:  // valuationTime
          return ((GlobalCycleOptions) bean).getValuationTime();
        case -178301862:  // valuationTimeSupplier
          return ((GlobalCycleOptions) bean).getValuationTimeSupplier();
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
   * The bean-builder for {@code GlobalCycleOptions}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<GlobalCycleOptions> {

    private boolean _awaitAllMarketData;
    private int _numCycles;
    private MarketDataSpecification _marketDataSpec;
    private ZonedDateTime _valuationTime;
    private Supplier<ZonedDateTime> _valuationTimeSupplier;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(GlobalCycleOptions beanToCopy) {
      this._awaitAllMarketData = beanToCopy.isAwaitAllMarketData();
      this._numCycles = beanToCopy.getNumCycles();
      this._marketDataSpec = beanToCopy.getMarketDataSpec();
      this._valuationTime = beanToCopy.getValuationTime();
      this._valuationTimeSupplier = beanToCopy.getValuationTimeSupplier();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -32240431:  // awaitAllMarketData
          return _awaitAllMarketData;
        case 780459891:  // numCycles
          return _numCycles;
        case 843018977:  // marketDataSpec
          return _marketDataSpec;
        case 113591406:  // valuationTime
          return _valuationTime;
        case -178301862:  // valuationTimeSupplier
          return _valuationTimeSupplier;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -32240431:  // awaitAllMarketData
          this._awaitAllMarketData = (Boolean) newValue;
          break;
        case 780459891:  // numCycles
          this._numCycles = (Integer) newValue;
          break;
        case 843018977:  // marketDataSpec
          this._marketDataSpec = (MarketDataSpecification) newValue;
          break;
        case 113591406:  // valuationTime
          this._valuationTime = (ZonedDateTime) newValue;
          break;
        case -178301862:  // valuationTimeSupplier
          this._valuationTimeSupplier = (Supplier<ZonedDateTime>) newValue;
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
    public GlobalCycleOptions build() {
      return new GlobalCycleOptions(
          _awaitAllMarketData,
          _numCycles,
          _marketDataSpec,
          _valuationTime,
          _valuationTimeSupplier);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the {@code awaitAllMarketData} property in the builder.
     * @param awaitAllMarketData  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder awaitAllMarketData(boolean awaitAllMarketData) {
      JodaBeanUtils.notNull(awaitAllMarketData, "awaitAllMarketData");
      this._awaitAllMarketData = awaitAllMarketData;
      return this;
    }

    /**
     * Sets the {@code numCycles} property in the builder.
     * @param numCycles  the new value
     * @return this, for chaining, not null
     */
    public Builder numCycles(int numCycles) {
      this._numCycles = numCycles;
      return this;
    }

    /**
     * Sets the {@code marketDataSpec} property in the builder.
     * @param marketDataSpec  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder marketDataSpec(MarketDataSpecification marketDataSpec) {
      JodaBeanUtils.notNull(marketDataSpec, "marketDataSpec");
      this._marketDataSpec = marketDataSpec;
      return this;
    }

    /**
     * Sets the {@code valuationTime} property in the builder.
     * @param valuationTime  the new value
     * @return this, for chaining, not null
     */
    public Builder valuationTime(ZonedDateTime valuationTime) {
      this._valuationTime = valuationTime;
      return this;
    }

    /**
     * Sets the {@code valuationTimeSupplier} property in the builder.
     * @param valuationTimeSupplier  the new value
     * @return this, for chaining, not null
     */
    public Builder valuationTimeSupplier(Supplier<ZonedDateTime> valuationTimeSupplier) {
      this._valuationTimeSupplier = valuationTimeSupplier;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(192);
      buf.append("GlobalCycleOptions.Builder{");
      buf.append("awaitAllMarketData").append('=').append(JodaBeanUtils.toString(_awaitAllMarketData)).append(',').append(' ');
      buf.append("numCycles").append('=').append(JodaBeanUtils.toString(_numCycles)).append(',').append(' ');
      buf.append("marketDataSpec").append('=').append(JodaBeanUtils.toString(_marketDataSpec)).append(',').append(' ');
      buf.append("valuationTime").append('=').append(JodaBeanUtils.toString(_valuationTime)).append(',').append(' ');
      buf.append("valuationTimeSupplier").append('=').append(JodaBeanUtils.toString(_valuationTimeSupplier));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
