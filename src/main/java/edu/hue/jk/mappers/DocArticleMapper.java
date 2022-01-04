package edu.hue.jk.mappers;

import edu.hue.jk.models.DocArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * 这个类无法实例化，现在不用了******************************
 */

/**
 * 查询和上传文章，本类不需要@Autowired注解
 */
public class DocArticleMapper {
    @Autowired
    private MongoOperations mongoOperations;

    /**
     * 保存一条article记录
     *
     * @param article
     * @return 保存成功返回 > 0
     */
    public int save(DocArticle article) {
        if (mongoOperations.save(article) != null) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 根据文章uuid来查询文章内容
     *
     * @param uuid
     * @return
     */
    public DocArticle getById(String uuid) {
        return mongoOperations.findById(uuid, DocArticle.class, "article");
    }

    /**
     * 根据用户id来查询所有的文章
     *
     * @param userid
     * @return
     */
    public List<DocArticle> getByUserid(Integer userid) {
        return mongoOperations.find(new Query(Criteria.where("userid").is(userid)), DocArticle.class, "article");
    }

    /**
     * 删除指定uuid的一篇文章
     *
     * @param uuid
     * @return
     */
    public Boolean deleteById(String uuid) {
        if (mongoOperations.findAndRemove(new Query(Criteria.where("userid").is(uuid)), DocArticle.class, "article") != null) {
            return true;
        } else {
            return false;
        }
    }
}
