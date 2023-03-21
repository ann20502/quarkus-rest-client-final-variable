# quarkus-rest-client-final-variable

`com.test.GetAccountInfoInput` uses `final variable` and it seems to be causing issues with rest-client.

### Issue 1

Encountered exception while compiling to native:

```
mvn package -Pnative -Dquarkus.native.container-build=true
```

Exception:
```
Fatal error: com.oracle.graal.pointsto.util.AnalysisError$ParsingError: Error encountered while parsing com.test.rest.GetAccountInfoInput.__quarkus_rest_inject(org.jboss.resteasy.reactive.server.injection.ResteasyReactiveInjectionContext) 
Parsing context:
   at com.test.rest.GetAccountInfoInput.__quarkus_rest_inject(GetAccountInfoInput.java)

Caused by: com.oracle.graal.pointsto.constraints.UnresolvedElementException: Discovered unresolved field during parsing: com.test.rest.GetAccountInfoInput.accountId. This error is reported at image build time because class com.test.rest.GetAccountInfoInput is registered for linking at image build time by command line
	at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.phases.SharedGraphBuilderPhase$SharedBytecodeParser.reportUnresolvedElement(SharedGraphBuilderPhase.java:333)
	at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.phases.SharedGraphBuilderPhase$SharedBytecodeParser.handleUnresolvedField(SharedGraphBuilderPhase.java:305)
	at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.phases.SharedGraphBuilderPhase$SharedBytecodeParser.handleUnresolvedStoreField(SharedGraphBuilderPhase.java:269)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.genPutField(BytecodeParser.java:4766)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.genPutField(BytecodeParser.java:4745)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.genPutField(BytecodeParser.java:4741)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.processBytecode(BytecodeParser.java:5285)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.iterateBytecodesForBlock(BytecodeParser.java:3385)
	... 27 more
```

```
Fatal error: com.oracle.graal.pointsto.util.AnalysisError$ParsingError: Error encountered while parsing com.test.rest.GetAccountInfoInput.__quarkus_rest_inject(org.jboss.resteasy.reactive.server.injection.ResteasyReactiveInjectionContext) 
Parsing context:
   at com.test.rest.GetAccountInfoInput.__quarkus_rest_inject(GetAccountInfoInput.java)
```

```
Caused by: org.graalvm.compiler.java.BytecodeParser$BytecodeParserError: com.oracle.graal.pointsto.constraints.UnresolvedElementException: Discovered unresolved field during parsing: com.test.rest.GetAccountInfoInput.accountId. This error is reported at image build time because class com.test.rest.GetAccountInfoInput is registered for linking at image build time by command line
	at parsing com.test.rest.GetAccountInfoInput.__quarkus_rest_inject(GetAccountInfoInput.java)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.throwParserError(BytecodeParser.java:2518)
	at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.phases.SharedGraphBuilderPhase$SharedBytecodeParser.throwParserError(SharedGraphBuilderPhase.java:110)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.iterateBytecodesForBlock(BytecodeParser.java:3393)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.handleBytecodeBlock(BytecodeParser.java:3345)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.processBlock(BytecodeParser.java:3190)
```

```
Caused by: com.oracle.graal.pointsto.constraints.UnresolvedElementException: Discovered unresolved field during parsing: com.test.rest.GetAccountInfoInput.accountId. This error is reported at image build time because class com.test.rest.GetAccountInfoInput is registered for linking at image build time by command line
	at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.phases.SharedGraphBuilderPhase$SharedBytecodeParser.reportUnresolvedElement(SharedGraphBuilderPhase.java:333)
	at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.phases.SharedGraphBuilderPhase$SharedBytecodeParser.handleUnresolvedField(SharedGraphBuilderPhase.java:305)
	at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.phases.SharedGraphBuilderPhase$SharedBytecodeParser.handleUnresolvedStoreField(SharedGraphBuilderPhase.java:269)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.genPutField(BytecodeParser.java:4766)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.genPutField(BytecodeParser.java:4745)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.genPutField(BytecodeParser.java:4741)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.processBytecode(BytecodeParser.java:5285)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.iterateBytecodesForBlock(BytecodeParser.java:3385)
	... 27 more
```

### Issue 2

Remove config below will encounter exception while running in `jvm mode`:

application.properties:
```
quarkus.arc.exclude-types=com.test.rest.GetAccountInfoInput
```

command:
```
mvn quarkus:dev
```

UnsatisfiedResolutionException:
```
javax.enterprise.inject.UnsatisfiedResolutionException: Unsatisfied dependency for type java.lang.String and qualifiers [@Default]
	- java member: com.test.rest.GetAccountInfoInput():accountId
	- declared on CLASS bean [types=[com.test.rest.GetAccountInfoInput, java.lang.Object], qualifiers=[@Default, @Any], target=com.test.rest.GetAccountInfoInput]
	The following beans match by type, but none have matching qualifiers:
		- Bean [class=java.lang.String, qualifiers=[@ConfigProperty, @Any]]
	at io.quarkus.arc.processor.Beans.resolveInjectionPoint(Beans.java:440)
	at io.quarkus.arc.processor.BeanInfo.init(BeanInfo.java:539)
	at io.quarkus.arc.processor.BeanDeployment.init(BeanDeployment.java:276)
	at io.quarkus.arc.processor.BeanProcessor.initialize(BeanProcessor.java:148)
	at io.quarkus.arc.deployment.ArcProcessor.validate(ArcProcessor.java:526)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
```
