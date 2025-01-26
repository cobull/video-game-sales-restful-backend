package com.codyb.videogamesalesapp.videogame;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record VideoGame(
        @JsonIgnore
        Integer id,
        @NotEmpty
        String title,
        @NotEmpty
        String console,
        @NotEmpty
        String genre,
        @Nullable
        String publisher,
        @Nullable
        String developer,
        @Nullable
        Double critic_score,
        @Nullable
        Double total_sales,
        @Nullable
        Double na_sales,
        @Nullable
        Double jp_sales,
        @Nullable
        Double pal_sales,
        @Nullable
        Double other_sales,
        @NotNull
        LocalDate release_date,
        @Nullable
        LocalDate last_update
) { }
