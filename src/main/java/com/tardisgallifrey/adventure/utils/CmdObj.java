package com.tardisgallifrey.adventure.utils;

import java.io.Serializable;

public record CmdObj( String verb, String noun1, String preposition, String adjective, String noun2 ) implements Serializable{}

