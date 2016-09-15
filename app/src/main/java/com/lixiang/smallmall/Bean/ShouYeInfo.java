package com.lixiang.smallmall.Bean;

import java.util.List;

/**
 * Created by wd794 on 2016/9/15 0015.
 */
public class ShouYeInfo {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {


        private List<ItemsBean> items;

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {


            private ComponentBean component;

            public ComponentBean getComponent() {
                return component;
            }

            public void setComponent(ComponentBean component) {
                this.component = component;
            }

            public static class ComponentBean {
                @Override
                public String toString() {
                    return "ComponentBean{" +
                            "title='" + title + '\'' +
                            ", items=" + items +
                            '}';
                }

                private String title;

                private List<ItemsBean2> items;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public List<ItemsBean2> getItems() {
                    return items;
                }

                public void setItems(List<ItemsBean2> items) {
                    this.items = items;
                }

                public static class ItemsBean2 {

                    @Override
                    public String toString() {
                        return "ItemsBean2{" +
                                "component=" + component +
                                '}';
                    }

                    private ComponentBean2 component;

                    public ComponentBean2 getComponent() {
                        return component;
                    }

                    public void setComponent(ComponentBean2 component) {
                        this.component = component;
                    }

                    public static class ComponentBean2 {
                        private String word;
                        private String picUrl;

                        @Override
                        public String toString() {
                            return "ComponentBean2{" +
                                    "word='" + word + '\'' +
                                    ", picUrl='" + picUrl + '\'' +
                                    '}';
                        }

                        public String getWord() {
                            return word;
                        }

                        public void setWord(String word) {
                            this.word = word;
                        }

                        public String getPicUrl() {
                            return picUrl;
                        }

                        public void setPicUrl(String picUrl) {
                            this.picUrl = picUrl;
                        }
                    }
                }
            }
        }
    }
}
