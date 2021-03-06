package org.bdickele.ambiguous.repository;

import org.bdickele.ambiguous.domain.AgreementRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Bertrand DICKELE
 */
public interface AgreementRuleRepository extends JpaRepository<AgreementRule, Long> {

    @Query("select r from AgreementRule r where r.destination.code = :destinationCode and r.goods.code = :goodsCode")
    AgreementRule findByDestinationCodeAndGoodsCode(@Param("destinationCode") String destinationCode,
                                                    @Param("goodsCode") String goodsCode);

    List<AgreementRule> findByDestinationCode(String destinationCode);

    List<AgreementRule> findByGoodsCode(String goodsCode);
}
