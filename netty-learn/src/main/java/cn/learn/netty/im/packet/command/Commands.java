// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: test/login_request.proto
// Protobuf Java Version: 4.26.1

package cn.learn.netty.im.packet.command;

public final class Commands {
  private Commands() {}
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 26,
      /* patch= */ 1,
      /* suffix= */ "",
      Commands.class.getName());
  }
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_cn_learn_netty_LoginRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_cn_learn_netty_LoginRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\030test/login_request.proto\022\016cn.learn.net" +
      "ty\"C\n\014LoginRequest\022\017\n\007user_id\030\001 \001(\003\022\020\n\010u" +
      "sername\030\002 \001(\t\022\020\n\010password\030\003 \001(\tB.\n cn.le" +
      "arn.netty.im.packet.commandB\010CommandsP\001b" +
      "\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_cn_learn_netty_LoginRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_cn_learn_netty_LoginRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_cn_learn_netty_LoginRequest_descriptor,
        new String[] { "UserId", "Username", "Password", });
    descriptor.resolveAllFeaturesImmutable();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
