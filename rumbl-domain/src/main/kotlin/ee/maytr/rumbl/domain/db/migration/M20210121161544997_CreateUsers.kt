package ee.maytr.rumbl.domain.db.migration

import com.improve_future.harmonica.core.AbstractMigration

/**
 * Migration
 */
class M20210121161544997_CreateUsers : AbstractMigration() {
    override fun up() {
        createTable("users") {
            varchar("email", nullable = false)
        }
    }

    override fun down() {
        dropTable("users")
    }
}
