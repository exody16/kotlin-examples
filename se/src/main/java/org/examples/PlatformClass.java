package org.examples;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
// works with import jakarta.validation.constraints.NotNull;

public class PlatformClass {

    public @NotNull String getNotNullPlatform() {
        return "not null;";
    }

    @Nullable
    public String getNullablePlatform() {
        return "nullable;";
    }

    public String getAnyPlatform() {
        return null;
    }

}
