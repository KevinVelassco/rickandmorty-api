package com.app.rickandmorty.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MetaResponse {
    private Long count;
    private Integer pages;
    private String next;
    private String prev;

    public static MetaResponse generate(Page<?> info, Integer page) {
        String url = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUriString();
        String nextUrl = info.hasNext() ? url+"?page="+(page+1) : null;
        String prevUrl = info.hasPrevious() ? url+"?page="+(page-1) : null;

        return MetaResponse.builder()
                .count(info.getTotalElements())
                .pages(info.getTotalPages())
                .next(nextUrl)
                .prev(prevUrl)
                .build();
    }
}
