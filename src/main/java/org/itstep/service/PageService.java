package org.itstep.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PageService {

    public List<Integer> getNumbersPages(int currentPage, int countPages) {
        List<Integer> numbersPage = new ArrayList<>();
        int start = currentPage - 2;
        int end = currentPage + 2;
        int elementCountOfPage = 5;


        if (countPages <= elementCountOfPage){
            start = 1;
            end = countPages;
        } else if (start < 1){
            start = 1;
            end = start + elementCountOfPage - 1;
        } else if (end > countPages){
            end = countPages;
            start = end - elementCountOfPage + 1;
        }

        for (; start <= end; start++) {
            numbersPage.add(start);
        }

            return numbersPage;

    }
}
