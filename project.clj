(defproject react-map-gl-failing "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.10.238"]
                 [reagent "0.7.0"]
                 [re-frame "0.10.5"]]

  :plugins [[lein-cljsbuild "1.1.7"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :figwheel {:css-dirs ["resources/public/css"]}

  :repl-options {:nrepl-middleware [cider.piggieback/wrap-cljs-repl]}

  :aliases {"dev" ["do" "clean"
                        ["pdo" ["figwheel" "dev"]]]
            "build" ["with-profile" "+prod,-dev" "do"
                          ["clean"]
                          ["cljsbuild" "once" "min"]]}

  :profiles
  {:dev
   {:dependencies [[binaryage/devtools "0.9.10"]
                   [day8.re-frame/re-frame-10x "0.3.3"]
                   [day8.re-frame/tracing "0.5.1"]
                   [figwheel-sidecar "0.5.16"]
                   [cider/piggieback "0.3.5"]]

    :plugins      [[lein-figwheel "0.5.16"]
                   [lein-pdo "0.1.1"]]}
   :prod {:dependencies [[day8.re-frame/tracing-stubs "0.5.1"]]}}

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "neptune.core/mount-root"}
     :compiler     {:main                 react-map-gl-failing.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true
                    :install-deps true
                    :npm-deps {"@cljs-oss/module-deps" "^1.1.1"
                               "react-map-gl" "^3.3.0-alpha.3"
                               "react" "^16.4.1"}
                    :preloads             [devtools.preload
                                           day8.re-frame-10x.preload]
                    :closure-defines      {"re_frame.trace.trace_enabled_QMARK_" true
                                           "day8.re_frame.tracing.trace_enabled_QMARK_" true}
                    :external-config      {:devtools/config {:features-to-install :all}}}}

    {:id           "min"
     :source-paths ["src/cljs"]
     :compiler     {:main            react-map-gl-failing.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :install-deps true
                    :npm-deps        {"@cljs-oss/module-deps" "^1.1.1"
                                      "react-map-gl" "^3.3.0-alpha.3"
                                      "react" "^16.4.1"}
                    :pretty-print    false}}]})
