package kr.co.numble.numble.global.utils.code;

import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import java.util.List;


public class PagingSupportUtil {

    public static <T> Slice<T> fetchSliceByCursor(JPAQuery<T> query, Pageable pageable){
        int pageSize = pageable.getPageSize();

        List<T> content = query
                .limit(pageable.getPageSize() + 1)
                .fetch();

        return new SliceImpl<>(content, pageable, isHasNext(pageSize, content));
    }

    public static <T> Slice<T> fetchSliceByOffset(JPAQuery<T> query, Pageable pageable){
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


}
