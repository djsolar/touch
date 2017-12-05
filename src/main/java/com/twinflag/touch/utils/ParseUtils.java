package com.twinflag.touch.utils;

import com.twinflag.touch.entity.ContentBean;
import com.twinflag.touch.entity.LevelOneBean;
import com.twinflag.touch.entity.LevelTwoBean;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.List;

public class ParseUtils {

    public List<LevelOneBean> parseMenu(String path) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(path);
        Element root = document.getRootElement();

        List<LevelOneBean> levelOneBeans = new ArrayList<>();
        List<Element> levelOneElements = root.elements("level-1");
        for(Element element : levelOneElements) {
            levelOneBeans.add(parseLevelOne(element));
        }
        return levelOneBeans;
    }

    private LevelOneBean parseLevelOne(Element node) {
        LevelOneBean levelOneBean = new LevelOneBean();
        String normalPic = node.attributeValue("iconurl_a");
        String selectedPic = node.attributeValue("iconurl_b");
        String url = node.attributeValue("url");
        levelOneBean.setNormalPic(normalPic);
        levelOneBean.setSelectedPic(selectedPic);
        levelOneBean.setUrl(url);

        List<Element> elements = node.elements("level-2");
        List<LevelTwoBean> levelTwoBeans = new ArrayList<>();
        for(Element element : elements) {
            LevelTwoBean levelTwoBean = parseLevelTwo(element);
            levelTwoBeans.add(levelTwoBean);
        }
        levelOneBean.setTwoContent(levelTwoBeans);
        return levelOneBean;
    }

    private LevelTwoBean parseLevelTwo(Element node) {
        LevelTwoBean levelTwoBean = new LevelTwoBean();
        String label = node.attributeValue("label");
        boolean isMany = Boolean.parseBoolean(node.attributeValue("ismany"));
        levelTwoBean.setLabel(label);
        levelTwoBean.setMany(isMany);

        List<Element> elements = node.elements("content");
        if (elements == null || elements.size() == 0) {
            String url = node.attributeValue("url");
            if (url != null) {
                levelTwoBean.setUrl(url);
            }
            String title = node.attributeValue("title");
            if (title != null) {
                levelTwoBean.setTitle(title);
            }
        } else {
            List<ContentBean> contentBeans = new ArrayList<>();
            for(Element element : elements) {
                ContentBean contentBean = parseContent(element);
                if (contentBean == null)
                    continue;
                contentBeans.add(contentBean);
            }
            levelTwoBean.setContentBeans(contentBeans);
        }
        return levelTwoBean;
    }

    private ContentBean parseContent(Element node) {
        String url = node.attributeValue("url");
        String label = node.attributeValue("label");
        String title = node.attributeValue("title");
        if (url != null && label != null && title != null) {
            ContentBean contentBean = new ContentBean();
            contentBean.setLabel(label);
            contentBean.setTitle(title);
            List<String> paths = new ArrayList<>();
            paths.add(url);
            contentBean.setType(getType(url));
            contentBean.setPaths(paths);
            return contentBean;
        }
        if (url == null) {
            ContentBean contentBean = new ContentBean();
            contentBean.setLabel(label);
            contentBean.setTitle(title);
            List<Element> manyPics = node.elements("manypic");
            List<String> paths = getPicPaths(manyPics);
            if (paths != null) {
                contentBean.setPaths(paths);
                contentBean.setType(SourceType.IMAGE.getType());
            }
            List<Element> manyTxts = node.elements("manytxt");
            paths = getPicPaths(manyTxts);
            if (paths != null) {
                contentBean.setPaths(paths);
                contentBean.setType(SourceType.TEXT.getType());
            }
            return contentBean;
        }

        if (label == null && title == null) {
            ContentBean contentBean = new ContentBean();
            List<String> paths = new ArrayList<>();
            paths.add(url);
            contentBean.setPaths(paths);
            contentBean.setType(SourceType.IMAGE.getType());
            return contentBean;
        }
        return null;
    }

    private List<String> getPicPaths(List<Element> elements) {
        if (elements != null && elements.size() > 0) {
            List<String> paths = new ArrayList<>();
            for(Element element : elements) {
                String picUrl = element.attributeValue("url");
                paths.add(picUrl);
            }
            return paths;
        }
        return null;
    }

    private int getType(String path) {
        if (path.endsWith(SourceType.TEXT.getSuffix())) {
            return SourceType.TEXT.getType();
        }
        if (path.endsWith(SourceType.IMAGE.getSuffix())) {
            return SourceType.IMAGE.getType();
        }
        return SourceType.NONE.getType();
    }


    public static void main(String[] args) throws DocumentException {
        ParseUtils parseUtils = new ParseUtils();
        List<LevelOneBean> levelOneBeans = parseUtils.parseMenu("C:\\Users\\zhouyiran\\IdeaProjects\\touch\\src\\main\\resources\\StreamingAssets\\menu.xml");
        for(LevelOneBean llc : levelOneBeans) {
            List<LevelTwoBean> levelTwoBeans = llc.getTwoContent();
            System.out.println(levelTwoBeans.size());
        }
    }
}
