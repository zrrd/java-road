// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: test/command.proto
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
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_cn_learn_netty_LoginResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_cn_learn_netty_LoginResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_cn_learn_netty_MessageRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_cn_learn_netty_MessageRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_cn_learn_netty_MessageResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_cn_learn_netty_MessageResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\022test/command.proto\022\016cn.learn.netty\"C\n\014" +
      "LoginRequest\022\017\n\007user_id\030\001 \001(\003\022\020\n\010usernam" +
      "e\030\002 \001(\t\022\020\n\010password\030\003 \001(\t\"0\n\rLoginRespon" +
      "se\022\017\n\007success\030\001 \001(\010\022\016\n\006reason\030\002 \001(\t\"!\n\016M" +
      "essageRequest\022\017\n\007message\030\001 \001(\t\"\"\n\017Messag" +
      "eResponse\022\017\n\007message\030\001 \001(\tB.\n cn.learn.n" +
      "etty.im.packet.commandB\010CommandsP\001b\006prot" +
      "o3"
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
    internal_static_cn_learn_netty_LoginResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_cn_learn_netty_LoginResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_cn_learn_netty_LoginResponse_descriptor,
        new String[] { "Success", "Reason", });
    internal_static_cn_learn_netty_MessageRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_cn_learn_netty_MessageRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_cn_learn_netty_MessageRequest_descriptor,
        new String[] { "Message", });
    internal_static_cn_learn_netty_MessageResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_cn_learn_netty_MessageResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_cn_learn_netty_MessageResponse_descriptor,
        new String[] { "Message", });
    descriptor.resolveAllFeaturesImmutable();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
