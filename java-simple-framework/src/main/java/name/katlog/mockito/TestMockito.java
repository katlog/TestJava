package name.katlog.mockito;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


/**
 * Created by fw on 2021/3/26
 */
public class TestMockito {

    /** 股票 */
    @Data
    @AllArgsConstructor
    private static class Stock {
        private String stockId;
        private String name;
        private int quantity;
    }

    /** 股票服务 */
    interface StockService {
        public double getPrice(Stock stock);
    }

    /** 股票投资组合 */
    @Data
    private static class Portfolio {
        private StockService stockService;
        private List<Stock> stocks;

        public double getMarketValue(){
            double marketValue = 0.0;
            for(Stock stock:stocks){
                marketValue += stockService.getPrice(stock) * stock.getQuantity();
            }
            return marketValue;
        }
    }


    Portfolio portfolio;
    StockService stockService;


    /** 基本使用 */
    @Test
    public void common(){
        //Create a portfolio object which is to be tested
        portfolio = new Portfolio();

        //Create the mock object of stock service
        stockService = mock(StockService.class);

        //set the stockService to the portfolio
        portfolio.setStockService(stockService);

        //Creates a list of stocks to be added to the portfolio
        List<Stock> stocks = new ArrayList<Stock>();
        Stock googleStock = new Stock("1","Google", 10);
        Stock microsoftStock = new Stock("2","Microsoft",100);

        stocks.add(googleStock);
        stocks.add(microsoftStock);

        //add stocks to the portfolio
        portfolio.setStocks(stocks);

        // 添加行为
        //mock the behavior of stock service to return the value of various stocks
        when(stockService.getPrice(googleStock)).thenReturn(50.00);
        when(stockService.getPrice(microsoftStock)).thenReturn(1000.00);

        double marketValue = portfolio.getMarketValue();
        Assert.assertEquals(100500.0, marketValue, 6);

    }




}
