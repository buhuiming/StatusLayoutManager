# StatusLayoutManager
This is StatusLayoutManager Project!

[![API](https://img.shields.io/badge/API-15%2B-brightgreen.svg)](https://android-arsenal.com/api?level=15) [![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)[ ![Download](https://api.bintray.com/packages/bikie/bhm-sdk/StatusLayoutManager/images/download.svg) ](https://bintray.com/bikie/bhm-sdk/StatusLayoutManager/_latestVersion)
-----

  以前的项目在常常遇见这样的场景，未登录显示<未登录页面>，无网络显示<网络异常页面>，
没有数据显示<无数据页面>等等，当场景多了，页面自然也多了，但是在每个layout文件中都
添加这些布局，或者通过include方式，始终都觉得太麻烦。

  所以自然就用了这个管理类，以下介绍下用法。

## 1.引用

    <dependency>
      <groupId>com.bhm.sdk.manager.library</groupId>
      <artifactId>StatusLayoutManager</artifactId>
      <version>1.0.2</version>
      <type>pom</type>
    </dependency>

或者

    compile 'com.bhm.sdk.manager.library:StatusLayoutManager:1.0.2'
    
## 2.在BaseActivity或者BaseFragment中声名对象

    protected StatusLayoutManager layoutManager;
    
## 3.抽象方法对布局的引用进行初始化：

        if(setRootViewId() != 0) {
            rootView = LayoutInflater.from(this).inflate(setRootViewId(), null, false);
            setContentView(rootView);
        }
        if(setContainerViewId() > 0 && setContentViewId() > 0) {
            layoutManager = StatusLayoutManager.newBuilder(this)
                    .rootView(rootView)//根布局
                    .containerViewId(setContainerViewId())//父布局
                    .contentViewId(setContentViewId())//内容布局
                    .itemViewsId(getItemViewsId())//布局集合
                    .OnViewClickListener(this)
                    .build();
        }
        
        
         private ArrayList<Integer>  getItemViewsId(){
         
            ArrayList<Integer> res = new ArrayList<>();
            res.add(R.layout.layout_no_data);
            res.add(R.layout.layout_no_net);
            return res;
         }

        protected abstract int setRootViewId();

        protected abstract int setContentViewId();

        protected abstract int setContainerViewId();
        
   其中getItemViewsId()方法，添加所需要用到的布局。
  
## 4.BaseActivity或者BaseFragment 实现接口OnViewClickListener，重写方法
      
       @Override
       public void onClick(View view) {
        
       }
       
## 5.在需要的场景切换布局：
    //添加到集合(getItemViewsId)的下标
    private final static int no_data = 0;
    private final static int no_net = 1;

    layoutManager.hideAllLayout();
    layoutManager.showViewByPosition(no_data);
    layoutManager.showViewByPosition(no_net);
        
        
## 6.布局中控件的点击事件：
 
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.layout_no_net :
                Toast.makeText(this, "点击了屏幕", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_no_data :
                Toast.makeText(this, "点击屏幕重试", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_no_data :
                Toast.makeText(this, "点击刷新", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    
    
## 注意：
    setRootViewId是根布局；
    containerViewId是父布局，比较关键的是此父布局最好使用LinearLayout；
    setContentViewId是内容布局，比如recycleView。

    至此，布局管理类的使用到此，是不是觉得简单多了！若不明白，请参考demo代码。

    如果出现布局空白，不显示指定的布局，可以修改或添加containerViewId对应的布局为LinearLayout。
    有问题，请发email给我873247376@qq.com。
