package com.board.bean.search;

import com.board.util.date.DateConvertUtil;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * <pre>
 *     기본 검색객체
 * </pre>
 *
 * @since 2025.09.17
 */
@Builder
@ToString
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
public class DefaultSearch {

    @Builder.Default
    private Integer currentPage = 1;

    @Builder.Default
    private Integer pageSize = 15;

    @Builder.Default
    private Integer totalCount = 0;

    @Builder.Default
    private String startDate = DateConvertUtil.format(LocalDate.now().minusMonths(1).minusDays(1));

    @Builder.Default
    private String endDate = DateConvertUtil.format(LocalDate.now());

    @Builder.Default
    private Boolean desc = true;

    @Builder.Default
    private Integer pageInterval = 5;

    /**
     * 페이징 없이 모든 목록의 조회가 필요할 경우 false 설정
     */
    @Builder.Default
    private Boolean usedPaging = true;

    /**
     * json 만들기용 조회일 경우 false
     */
    @Builder.Default
    private Boolean notJson = true;

    private Integer sid;

    private String searchKey;

    private String searchValue;

    private Integer sortColumn;

    /**
     * 관리자 sid
     *
     * @since 2025.07.23
     */
    private Integer adminSid;

    public Integer getLimit() {
        return (pageSize != null && pageSize > 0) ? pageSize : 15;
    }

    public Integer getOffset() {
        if (currentPage == null || currentPage < 1 || pageSize == null || pageSize <= 0) {
            return null;
        }
        return (currentPage - 1) * pageSize;
    }

    public Integer getMinPage() {
        if (currentPage == null || pageSize == null) {
            return 1;
        } else {
            return ((currentPage - 1) / pageInterval) * pageInterval + 1;
        }
    }

    // [2025.07.07] 데이터 없을 때 최소 1 페이지 표기되게 수정.
    public Integer getLastPage() {
        return Math.max(1, (int) Math.ceil((double) totalCount / pageSize));
    }

    public Integer getMaxPage() {
        return Math.min(getMinPage() + pageInterval - 1, getLastPage());
    }

    // [2025.06.30] 날짜검색 기본값 세팅 추가
    public void setDefaultDate() {
        LocalDate end = LocalDate.now();
        this.endDate = DateConvertUtil.format(end);

        LocalDate start = LocalDate.now().minusMonths(1).minusDays(1);
        this.startDate = DateConvertUtil.format(start);
    }

    public DefaultSearch() {
        this.currentPage = 1;
        this.pageSize = 15;
        this.totalCount = 0;
        this.desc = true;
        this.pageInterval = 5;
        this.usedPaging = true;
        this.notJson = true;

        this.setDefaultDate();
    }
}
