/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.web.bundle;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ws.rs.core.UriInfo;

import org.joda.beans.BeanDefinition;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.BasicMetaBean;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectMetaProperty;

/**
 * Data class for web-based bundles.
 */
@BeanDefinition
public class WebBundlesData extends DirectBean {

  /**
   * The bundle manager.
   */
  @PropertyDefinition
  private BundleManager _bundleManager;
  /**
   * The compressed bundle source.
   */
  @PropertyDefinition
  private CompressedBundleSource _compressedBundleSource;
  /**
   * The deployment mode.
   */
  @PropertyDefinition
  private DeployMode _mode;
  /**
   * The style tag.
   */
  @PropertyDefinition
  private StyleTag _styleTag;
  /**
   * The script tag.
   */
  @PropertyDefinition
  private ScriptTag _scriptTag;
  /**
   * The JSR-311 URI information.
   */
  @PropertyDefinition
  private UriInfo _uriInfo;

  /**
   * Creates an instance.
   */
  public WebBundlesData() {
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code WebBundlesData}.
   * @return the meta-bean, not null
   */
  public static WebBundlesData.Meta meta() {
    return WebBundlesData.Meta.INSTANCE;
  }

  @Override
  public WebBundlesData.Meta metaBean() {
    return WebBundlesData.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName) {
    switch (propertyName.hashCode()) {
      case 1459962059:  // bundleManager
        return getBundleManager();
      case -1666949794:  // compressedBundleSource
        return getCompressedBundleSource();
      case 3357091:  // mode
        return getMode();
      case 1997897769:  // styleTag
        return getStyleTag();
      case 249937615:  // scriptTag
        return getScriptTag();
      case -173275078:  // uriInfo
        return getUriInfo();
    }
    return super.propertyGet(propertyName);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue) {
    switch (propertyName.hashCode()) {
      case 1459962059:  // bundleManager
        setBundleManager((BundleManager) newValue);
        return;
      case -1666949794:  // compressedBundleSource
        setCompressedBundleSource((CompressedBundleSource) newValue);
        return;
      case 3357091:  // mode
        setMode((DeployMode) newValue);
        return;
      case 1997897769:  // styleTag
        setStyleTag((StyleTag) newValue);
        return;
      case 249937615:  // scriptTag
        setScriptTag((ScriptTag) newValue);
        return;
      case -173275078:  // uriInfo
        setUriInfo((UriInfo) newValue);
        return;
    }
    super.propertySet(propertyName, newValue);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the bundle manager.
   * @return the value of the property
   */
  public BundleManager getBundleManager() {
    return _bundleManager;
  }

  /**
   * Sets the bundle manager.
   * @param bundleManager  the new value of the property
   */
  public void setBundleManager(BundleManager bundleManager) {
    this._bundleManager = bundleManager;
  }

  /**
   * Gets the the {@code bundleManager} property.
   * @return the property, not null
   */
  public final Property<BundleManager> bundleManager() {
    return metaBean().bundleManager().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the compressed bundle source.
   * @return the value of the property
   */
  public CompressedBundleSource getCompressedBundleSource() {
    return _compressedBundleSource;
  }

  /**
   * Sets the compressed bundle source.
   * @param compressedBundleSource  the new value of the property
   */
  public void setCompressedBundleSource(CompressedBundleSource compressedBundleSource) {
    this._compressedBundleSource = compressedBundleSource;
  }

  /**
   * Gets the the {@code compressedBundleSource} property.
   * @return the property, not null
   */
  public final Property<CompressedBundleSource> compressedBundleSource() {
    return metaBean().compressedBundleSource().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the deployment mode.
   * @return the value of the property
   */
  public DeployMode getMode() {
    return _mode;
  }

  /**
   * Sets the deployment mode.
   * @param mode  the new value of the property
   */
  public void setMode(DeployMode mode) {
    this._mode = mode;
  }

  /**
   * Gets the the {@code mode} property.
   * @return the property, not null
   */
  public final Property<DeployMode> mode() {
    return metaBean().mode().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the style tag.
   * @return the value of the property
   */
  public StyleTag getStyleTag() {
    return _styleTag;
  }

  /**
   * Sets the style tag.
   * @param styleTag  the new value of the property
   */
  public void setStyleTag(StyleTag styleTag) {
    this._styleTag = styleTag;
  }

  /**
   * Gets the the {@code styleTag} property.
   * @return the property, not null
   */
  public final Property<StyleTag> styleTag() {
    return metaBean().styleTag().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the script tag.
   * @return the value of the property
   */
  public ScriptTag getScriptTag() {
    return _scriptTag;
  }

  /**
   * Sets the script tag.
   * @param scriptTag  the new value of the property
   */
  public void setScriptTag(ScriptTag scriptTag) {
    this._scriptTag = scriptTag;
  }

  /**
   * Gets the the {@code scriptTag} property.
   * @return the property, not null
   */
  public final Property<ScriptTag> scriptTag() {
    return metaBean().scriptTag().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the JSR-311 URI information.
   * @return the value of the property
   */
  public UriInfo getUriInfo() {
    return _uriInfo;
  }

  /**
   * Sets the JSR-311 URI information.
   * @param uriInfo  the new value of the property
   */
  public void setUriInfo(UriInfo uriInfo) {
    this._uriInfo = uriInfo;
  }

  /**
   * Gets the the {@code uriInfo} property.
   * @return the property, not null
   */
  public final Property<UriInfo> uriInfo() {
    return metaBean().uriInfo().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code WebBundlesData}.
   */
  public static class Meta extends BasicMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code bundleManager} property.
     */
    private final MetaProperty<BundleManager> _bundleManager = DirectMetaProperty.ofReadWrite(this, "bundleManager", BundleManager.class);
    /**
     * The meta-property for the {@code compressedBundleSource} property.
     */
    private final MetaProperty<CompressedBundleSource> _compressedBundleSource = DirectMetaProperty.ofReadWrite(this, "compressedBundleSource", CompressedBundleSource.class);
    /**
     * The meta-property for the {@code mode} property.
     */
    private final MetaProperty<DeployMode> _mode = DirectMetaProperty.ofReadWrite(this, "mode", DeployMode.class);
    /**
     * The meta-property for the {@code styleTag} property.
     */
    private final MetaProperty<StyleTag> _styleTag = DirectMetaProperty.ofReadWrite(this, "styleTag", StyleTag.class);
    /**
     * The meta-property for the {@code scriptTag} property.
     */
    private final MetaProperty<ScriptTag> _scriptTag = DirectMetaProperty.ofReadWrite(this, "scriptTag", ScriptTag.class);
    /**
     * The meta-property for the {@code uriInfo} property.
     */
    private final MetaProperty<UriInfo> _uriInfo = DirectMetaProperty.ofReadWrite(this, "uriInfo", UriInfo.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<Object>> _map;

    @SuppressWarnings({"unchecked", "rawtypes" })
    protected Meta() {
      LinkedHashMap temp = new LinkedHashMap();
      temp.put("bundleManager", _bundleManager);
      temp.put("compressedBundleSource", _compressedBundleSource);
      temp.put("mode", _mode);
      temp.put("styleTag", _styleTag);
      temp.put("scriptTag", _scriptTag);
      temp.put("uriInfo", _uriInfo);
      _map = Collections.unmodifiableMap(temp);
    }

    @Override
    public WebBundlesData createBean() {
      return new WebBundlesData();
    }

    @Override
    public Class<? extends WebBundlesData> beanType() {
      return WebBundlesData.class;
    }

    @Override
    public Map<String, MetaProperty<Object>> metaPropertyMap() {
      return _map;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code bundleManager} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<BundleManager> bundleManager() {
      return _bundleManager;
    }

    /**
     * The meta-property for the {@code compressedBundleSource} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<CompressedBundleSource> compressedBundleSource() {
      return _compressedBundleSource;
    }

    /**
     * The meta-property for the {@code mode} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<DeployMode> mode() {
      return _mode;
    }

    /**
     * The meta-property for the {@code styleTag} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<StyleTag> styleTag() {
      return _styleTag;
    }

    /**
     * The meta-property for the {@code scriptTag} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ScriptTag> scriptTag() {
      return _scriptTag;
    }

    /**
     * The meta-property for the {@code uriInfo} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<UriInfo> uriInfo() {
      return _uriInfo;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
