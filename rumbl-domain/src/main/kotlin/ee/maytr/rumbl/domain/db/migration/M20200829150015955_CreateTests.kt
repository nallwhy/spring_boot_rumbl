package ee.maytr.rumbl.domain.db.migration

import com.improve_future.harmonica.core.AbstractMigration

/**
 * Migration
 */
class M20200829150015955_CreateTests : AbstractMigration() {
    override fun up() {
        createTable("tests") {
            integer("integer_column", default = 8)
            varchar("varchar_column", size = 10, nullable = false)
            decimal("decimal_column", precision = 5, scale = 2)
            text("text_column", default = "default value")
        }
    }

    override fun down() {
        dropTable("tests")
    }
}
