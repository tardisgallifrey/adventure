package com.tardisgallifrey.adventure.utils;

import java.io.Serializable;

import com.tardisgallifrey.adventure.Thing;
import com.tardisgallifrey.adventure.ThingHolder;

public record ThingandThingHolder ( Thing aThing, ThingHolder aThingHolder ) implements Serializable{}

