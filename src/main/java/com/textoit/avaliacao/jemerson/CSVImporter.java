package com.textoit.avaliacao.jemerson;

import javax.batch.api.listener.JobListener;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.validation.BindException;

import com.textoit.avaliacao.jemerson.model.Movie;

@Configuration
@EnableBatchProcessing
public class CSVImporter {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	public JobListener listener;
	
	@Autowired
    public DataSource dataSource;

	@Value("classpath:/input/movielist.csv")
	private Resource inputResource;
	
	private final String[] fieldNames = { "year", "title", "studios", "producers", "winner" };

	@Bean
	public FieldSetMapper<Movie> fieldSetMapper() {
		return new FieldSetMapper<Movie>() {
			@Override
			public Movie mapFieldSet(FieldSet fieldSet) throws BindException {
				Movie movie = new Movie();
				movie.setYear(fieldSet.readShort("year"));
				movie.setTitle(fieldSet.readString("title"));
				movie.setProducers(fieldSet.readString("producers"));
				movie.setStudios(fieldSet.readString("studios"));
				movie.setWinner(fieldSet.readString("winner").equals("yes"));
				return movie;
			}
		};
	}

	@Bean
	public LineMapper<Movie> lineMapper() {
		DefaultLineMapper<Movie> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(fieldNames);
		lineTokenizer.setDelimiter(";");
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper());
		return lineMapper;
	}
	
	@Bean
    public FlatFileItemReader<Movie> reader() {
        return new FlatFileItemReaderBuilder<Movie>()
                .name("movieItemReader")
                .resource(inputResource)
                .linesToSkip(1)
                .delimited()
                .names(fieldNames)
                .lineMapper(lineMapper())
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Movie>() {{
                    setTargetType(Movie.class);
                }})
                .strict(false)
                .build();
    }
	
	@Bean
	public ItemProcessor<Movie, Movie> processor(){
		return new ItemProcessor<Movie, Movie>(){

			@Override
			public Movie process(Movie item) throws Exception {
				System.out.println("processando filme " + item);
				return item;
			}
			
		};
	}
	
	@Bean
    public JdbcBatchItemWriter<Movie> writer() {
        return new JdbcBatchItemWriterBuilder<Movie>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO movies (year, title, studios, producers, winner) VALUES (:year, :title, :studios, :producers, :winner)")
                .dataSource(dataSource)
                .build();
    }

	@Bean
	public Job importJob() {
		return jobBuilderFactory
				.get("readCSV")
				.incrementer(new RunIdIncrementer())
				.flow(step1())
				.end()
				.build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").
				<Movie, Movie>chunk(10)
				.reader(reader())
				//.processor(processor())
				.writer(writer()).build();
	}

}
