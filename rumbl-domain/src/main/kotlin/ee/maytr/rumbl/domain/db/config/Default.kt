package ee.maytr.rumbl.domain.db.config

import com.improve_future.harmonica.core.DbConfig
import com.improve_future.harmonica.core.Dbms

class Default : DbConfig({
    dbms = Dbms.PostgreSQL
    dbName = "rumbl-local"
    host = "127.0.0.1"
    port = 45432
    user = "postgres"
    password = "postgres"
})
