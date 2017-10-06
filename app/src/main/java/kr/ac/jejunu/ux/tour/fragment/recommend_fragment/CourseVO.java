package kr.ac.jejunu.ux.tour.fragment.recommend_fragment;

/**
 * Created by Osy on 2017-10-04.
 */

public class CourseVO {
    private int courseImgId;
    private String title;
    private String subTitle;
    private String info;

    public int getCourseImgId() {
        return courseImgId;
    }

    public void setCourseImgId(int courseImgId) {
        this.courseImgId = courseImgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    //샘플데이터 제작용 빌더
    public static class SampleBuilder {
        private int courseImgId;
        private String title;
        private String subTitle;
        private String info;

        public SampleBuilder setCourseImgId(int courseImgId) {
            this.courseImgId = courseImgId;

            return this;
        }

        public SampleBuilder setTitle(String title) {
            this.title = title;

            return this;
        }

        public SampleBuilder setSubTitle(String subTitle) {
            this.subTitle = subTitle;

            return this;
        }

        public SampleBuilder setInfo(String info) {
            this.info = info;

            return this;
        }

        public CourseVO build(){
            CourseVO vo = new CourseVO();

            vo.setCourseImgId(courseImgId);
            vo.setTitle(title);
            vo.setSubTitle(subTitle);
            vo.setInfo(info);

            return vo;
        }
    }
}
