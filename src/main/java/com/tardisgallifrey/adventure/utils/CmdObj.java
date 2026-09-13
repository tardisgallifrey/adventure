package com.tardisgallifrey.adventure.utils;

import java.io.Serializable;

public record CmdObj( String verb, String preposition, String adjective, String noun ) implements Serializable{}

