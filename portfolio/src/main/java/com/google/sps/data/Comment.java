package com.google.sps.data;

/** an instance of a Comment */
public final class Comment {

  private final long id;
  private final String username;
  private final String text;
  private final long timestamp;

  public Comment(long id, String username, String text, long timestamp) {
    this.id = id;
    this.username = username;
    this.text = text;
    this.timestamp = timestamp;
  }
}