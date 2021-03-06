package com.gssg.gssgbe.common.util;

import static com.querydsl.core.types.Order.*;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;

import com.querydsl.core.types.Order;

import lombok.experimental.UtilityClass;

@UtilityClass
public class QuerydslUtil {

	public static <T> Slice<T> createSlice(final List<T> content, final Pageable pageable) {
		boolean hasNext = false;

		if (content.size() > pageable.getPageSize()) {
			content.remove(content.size() - 1);
			hasNext = true;
		}
		return new SliceImpl<>(content, pageable, hasNext);
	}

	@NotNull
	public static Order getDirection(final Sort.Order order) {
		return order.getDirection().isAscending() ? ASC : DESC;
	}
}
