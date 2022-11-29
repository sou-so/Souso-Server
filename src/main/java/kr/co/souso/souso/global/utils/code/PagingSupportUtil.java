package kr.co.souso.souso.global.utils.code;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;


public class PagingSupportUtil {

    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final long DEFAULT_CURSOR_ID = Long.MAX_VALUE;

    public static <T> Slice<T> fetchSliceByCursor(JPAQuery<T> query, Pageable pageable) {
        int pageSize = pageable.getPageSize();

        List<T> content = query
                .limit(pageable.getPageSize() + 1)
                .fetch();

        return new SliceImpl<>(content, pageable, isHasNext(pageSize, content));
    }

    public static <T> Slice<T> fetchSliceByOffset(JPAQuery<T> query, Pageable pageable) {
        int pageSize = pageable.getPageSize();

        List<T> content = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        return new SliceImpl<>(content, pageable, isHasNext(pageSize, content));
    }

    private static <T> boolean isHasNext(int pageSize, List<T> content) {
        boolean hasNext = false;
        if (pageSize < content.size()) {
            hasNext = true;
            content.remove(pageSize);
        }
        return hasNext;
    }

    public static OrderSpecifier<?> getSortedColumn(Order order, Path<?> parent, String fieldName) {
        Path<Object> fieldPath = Expressions.path(Object.class, parent, fieldName);
        return new OrderSpecifier(order, fieldPath);
    }

    public static Long applyCursorId(Long cursorId) {
        return cursorId == 0 ? DEFAULT_CURSOR_ID : cursorId;
    }

    public static Pageable applyPageSize() {
        return PageRequest.of(0, DEFAULT_PAGE_SIZE);
    }
}