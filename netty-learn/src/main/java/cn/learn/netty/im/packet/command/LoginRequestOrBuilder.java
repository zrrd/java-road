// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: test/command.proto

// Protobuf Java Version: 4.26.1
package cn.learn.netty.im.packet.command;

public interface LoginRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:cn.learn.netty.LoginRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 user_id = 1;</code>
   * @return The userId.
   */
  long getUserId();

  /**
   * <code>string username = 2;</code>
   * @return The username.
   */
  String getUsername();
  /**
   * <code>string username = 2;</code>
   * @return The bytes for username.
   */
  com.google.protobuf.ByteString
      getUsernameBytes();

  /**
   * <code>string password = 3;</code>
   * @return The password.
   */
  String getPassword();
  /**
   * <code>string password = 3;</code>
   * @return The bytes for password.
   */
  com.google.protobuf.ByteString
      getPasswordBytes();
}
