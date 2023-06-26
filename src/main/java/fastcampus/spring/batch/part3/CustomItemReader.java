package fastcampus.spring.batch.part3;


import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.ArrayList;
import java.util.List;

public class CustomItemReader<T> implements ItemReader<T> {
    //이 아이템 리더는 자바 컬렉션의 리스트를 리더로 처리하는리더로 만들겠다

    private final List<T> items;

    public CustomItemReader(List<T> items){
        this.items = new ArrayList<>(items);
    }

    @Override
    public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(!items.isEmpty()) {
            return items.remove(0); // remove 메서드는 값을 반환하는 동시에 제거한다
        }

        return null; // null 을 리턴하면 chunk 메서드에서 끝난다
    }


}
