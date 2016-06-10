(defproject russellfail "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :source-paths ["src/cljs" "dev"]

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-3058" :scope "provided"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [ring "1.3.1"]
                 [compojure "1.2.0"]
                 [figwheel "0.2.5"]
                 [environ "1.0.0"]
                 [leiningen "2.5.0"]
                 [re-frame "0.2.0"]
                 [secretary "1.2.1"]
                 [reagent "0.5.0-alpha3"]]

  :min-lein-version "2.5.0"

  :plugins [[lein-cljsbuild "1.0.4"]
            [lein-environ "1.0.0"]
            [lein-figwheel "0.2.0-SNAPSHOT"]]

  :figwheel {:http-server-root "public"
             :css-dirs ["resources/public/css"]
             :port 3449}

  :cljsbuild {:builds {:app {:source-paths ["src/cljs" "dev"]
                             :compiler {:output-to "resources/public/js/app.js"
                                        :output-dir "resources/public/js/out"
                                        :source-map    "resources/public/js/out.js.map"
                                        :optimizations :none}}
                       :prod {:source-paths ["src/cljs"]
                              :compiler {:output-to "resources/public/js/russellfail.min.js"
                                         :optimizations :advanced}}}}
  )
