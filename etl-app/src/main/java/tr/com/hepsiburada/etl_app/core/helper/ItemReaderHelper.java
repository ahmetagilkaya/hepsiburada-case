package tr.com.hepsiburada.etl_app.core.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class ItemReaderHelper {

    public <I> JdbcCursorItemReader<I> createJdbcCursorItemReader(DataSource dataSource, String readerName, String sql, RowMapper<I> rowMapper) {
        JdbcCursorItemReader<I> itemReader = new JdbcCursorItemReader<I>();
        itemReader.setName(readerName);
        itemReader.setDataSource(dataSource);
        itemReader.setSql(sql);
        itemReader.setRowMapper(rowMapper);
        return itemReader;
    }

    public <I> ItemReader<I> createItemReader(DataSource dataSource, String readerName, String sql, Class<I> mappedClass) {
        return new JdbcCursorItemReaderBuilder<I>()
                .name(readerName)
                .dataSource(dataSource)
                .sql(sql)
                .rowMapper(new BeanPropertyRowMapper<>(mappedClass))
                .build();
    }

}
