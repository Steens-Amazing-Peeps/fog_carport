import app.web.entities.AccountInfo;
import app.web.entities.FullHistory;
import app.web.entities.Order;
import app.web.exceptions.DatabaseException;
import app.web.persistence.ConnectionPool;
import app.web.persistence.mappers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class EntityFullHistoryTest
{
    
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private final DataStore dataStore = new DataStoreImpl( CONNECTION_POOL );
    private final AccountInfoMapper accountInfoMapper = new AccountInfoMapperImpl( this.dataStore );
    
    private final BomMapper bomMapper = new BomMapperImpl( this.dataStore );
    
    private final CarportMapper carportMapper = new CarportMapperImpl( this.dataStore, this.bomMapper );
    
    private final OrderMapper orderMapper = new OrderMapperImpl( this.dataStore, this.carportMapper, this.bomMapper, this.accountInfoMapper );
    
    private final FullHistoryMapper fullHistoryMapper = new FullHistoryMapperImpl( this.accountInfoMapper, this.orderMapper );
    
    private Map< Integer, FullHistory > fullHistoryMap;
    
    @BeforeEach
    void setUp()
    {
        try {
            this.fullHistoryMap = this.fullHistoryMapper.readAllFull();
        } catch ( DatabaseException e ) {
            throw new RuntimeException( e );
        }
    }
    

    void mapLoopsTest()
    {
        for ( Map.Entry< Integer, FullHistory > fullHistoryEntry : fullHistoryMap.entrySet() ) {
            
            System.out.println();
            System.out.println();
            System.out.println( "------- 1 - NEW FULL HISTORY ENTRY------" );
            Integer userId = fullHistoryEntry.getKey();
            System.out.println( "fullHistoryEntry Key = " + userId );
            
            FullHistory fullHistory = fullHistoryEntry.getValue();
            System.out.println( "fullHistoryEntry Value = " + fullHistory );
            
            for ( Map.Entry< AccountInfo, Map< Integer, Order > > accountInfoOrderEntry : fullHistoryEntry.getValue().getOrdersByAccountInfo().entrySet() ) {
                
                System.out.println();
                System.out.println();
                System.out.println( "------ 2 - NEW ACCOUNT INFO ORDER Entry------" );
                AccountInfo accountInfo = accountInfoOrderEntry.getKey();
                System.out.println( "accountInfoOrderEntry Key = " + accountInfo );
                
                Map< Integer, Order > orders = accountInfoOrderEntry.getValue();
                System.out.println( "accountInfoOrderEntry Value = " + orders );
                
                for ( Map.Entry< Integer, Order > OrderEntry : accountInfoOrderEntry.getValue().entrySet() ) {
                    System.out.println();
                    System.out.println();
                    System.out.println( "------ 3 - NEW ORDER ENTRY------" );
                    Integer orderId = OrderEntry.getKey();
                    System.out.println( "OrderEntry Key = " + orderId );
                    
                    Order order = OrderEntry.getValue();
                    System.out.println( "OrderEntry Value = " + order );
                    
                }
            }
        }
        
        String HTML_SUGGESTED = "<div th:each=\"fullHistoryEntry : ${session.fullHistoryMap}\">\n" +
                                 "    <div th:value=\"fullHistory.key\">\n" +
                                 "        <!-- User Id, can be null -->\n" +
                                 "    </div>\n" +
                                 "\n" +
                                 "    <div th:value=\"fullHistory.value\">\n" +
                                 "        <!-- FullHistory Entity-->\n" +
                                 "    </div>\n" +
                                 "\n" +
                                 "    <div th:each=\"acountInfoAndOrdersEntry : ${fullHistoryEntry.value.getOrdersByAccountInfo}\">\n" +
                                 "\n" +
                                 "        <div th:value=\"acountInfoAndOrdersEntry.key\">\n" +
                                 "            <!-- AccountInfo Entity -->\n" +
                                 "        </div>\n" +
                                 "        <div th:value=\"acountInfoAndOrdersEntry.value\">\n" +
                                 "            <!-- Map< orderId, Order > -->\n" +
                                 "        </div>\n" +
                                 "\n" +
                                 "        <div th:each=\"orderEntry : ${acountInfoAndOrdersEntry.value}\">\n" +
                                 "\n" +
                                 "            <div th:value=\"orderEntry.key\">\n" +
                                 "                <!-- Order Id -->\n" +
                                 "            </div>\n" +
                                 "\n" +
                                 "            <div th:value=\"orderEntry.value\">\n" +
                                 "                <!-- Order Entity -->\n" +
                                 "            </div>\n" +
                                 "\n" +
                                 "        </div>\n" +
                                 "\n" +
                                 "    </div>\n" +
                                 "\n" +
                                 "</div>";
    }
    
}
