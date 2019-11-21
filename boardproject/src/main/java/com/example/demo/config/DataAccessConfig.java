package com.example.demo.config;
/*configuration, controller, restcontroller, bean, component
 * service, repository
 * 이런 어노테이션은 니가 new 하지마 스프링컨테이너가 싱글턴으로 관리해줌. 일단 그렇게만 이해*/

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

//DAO객체가 모여있는곳 + Mapper가 있는 곳을 세팅해줘야 한다.
//세팅 방법은 달라질 수 있다. 두개를 세팅해줘야 한다는 걸 이해하고 있어야 한다.

@Configuration
//DataAccessObject가 모여있는 곳이라고 생각하면 됨. 예전에 우리는 DAO라고 이름지었는데 여기선 repository라고 한다.
@MapperScan(basePackages = "com.example.demo.repository") 
public class DataAccessConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {

        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

        sessionFactory.setDataSource(dataSource);
        //classpath: 라고하면 디폴트로 resources가 잡힌다.
        sessionFactory.setMapperLocations(
        		new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
