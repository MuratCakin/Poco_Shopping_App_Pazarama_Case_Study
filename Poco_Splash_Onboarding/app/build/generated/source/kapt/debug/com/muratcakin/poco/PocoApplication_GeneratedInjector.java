package com.muratcakin.poco;

import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedEntryPoint;

@OriginatingElement(
    topLevelClass = PocoApplication.class
)
@GeneratedEntryPoint
@InstallIn(SingletonComponent.class)
public interface PocoApplication_GeneratedInjector {
  void injectPocoApplication(PocoApplication pocoApplication);
}
